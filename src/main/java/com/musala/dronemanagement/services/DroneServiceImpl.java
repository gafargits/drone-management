package com.musala.dronemanagement.services;

import com.musala.dronemanagement.entities.DroneEntity;
import com.musala.dronemanagement.entities.DroneState;
import com.musala.dronemanagement.entities.MedicationEntity;
import com.musala.dronemanagement.exceptions.DroneNotFoundException;
import com.musala.dronemanagement.exceptions.DroneRequestException;
import com.musala.dronemanagement.exceptions.MedicationNotFoundException;
import com.musala.dronemanagement.models.request.DroneRegistrationRequest;
import com.musala.dronemanagement.models.request.LoadDroneRequest;
import com.musala.dronemanagement.models.response.BatteryLevelResponse;
import com.musala.dronemanagement.models.response.DroneRegistrationResponse;
import com.musala.dronemanagement.models.response.LoadDroneResponse;
import com.musala.dronemanagement.models.response.MedicationResponse;
import com.musala.dronemanagement.repository.DroneRepository;
import com.musala.dronemanagement.repository.MedicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository, MedicationRepository medicationRepository, ModelMapper modelMapper) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DroneRegistrationResponse registerDrone(DroneRegistrationRequest drone) {
        DroneEntity droneEntity = modelMapper.map(drone, DroneEntity.class);
        DroneEntity existing = droneRepository.findBySerialNumber(droneEntity.getSerialNumber());
        if(existing != null){
            throw new DroneRequestException("Drone already registered. You can only register a drone once.");
        }
        droneEntity.setState(DroneState.IDLE);
        return modelMapper.map(droneRepository.save(droneEntity), DroneRegistrationResponse.class);
    }

    @Override
    public LoadDroneResponse loadDrone(LoadDroneRequest loadDroneRequest) {
        DroneRegistrationResponse drone = droneRepository.findById(loadDroneRequest.getDroneId())
                .map(d -> modelMapper.map(d, DroneRegistrationResponse.class))
                .orElseThrow(() -> new DroneNotFoundException("You can't load an unregistered drone"));

        if (drone.getBatteryCapacity() < 25) {
            throw new DroneRequestException("Drone with less than 25% battery capacity cannot be loaded");
        }
        if (drone.getState() != DroneState.IDLE  && drone.getState() != DroneState.LOADED) {
                throw new DroneRequestException("Drone can only be loaded when idle or loaded but with space to accommodate more medications");
        }
        MedicationEntity medicationEntity = medicationRepository.findByCode(loadDroneRequest.getCode());
        if (medicationEntity == null) {
            throw new MedicationNotFoundException("Medication not yet registered. You cannot load an unregistered medication");
        }
        drone.setState(DroneState.LOADING);
        double droneCurrentMedicationWeight = drone.getMedications().stream()
                .mapToDouble(MedicationResponse::getWeight)
                .reduce(0.0, Double::sum);
        if ((droneCurrentMedicationWeight + medicationEntity.getWeight()) >= drone.getWeight()) {
            throw new DroneRequestException("Medication too heavy for this drone, check other drones.");
        }
        DroneEntity droneEntity = modelMapper.map(drone, DroneEntity.class);
        droneEntity.getMedications().add(medicationEntity);
        droneEntity.setState(DroneState.LOADED);
        return modelMapper.map(droneRepository.saveAndFlush(droneEntity), LoadDroneResponse.class);
    }

    @Override
    public List<MedicationResponse> checkMedicationLoaded(UUID droneId) {
        DroneRegistrationResponse drone = modelMapper.map(droneRepository
                .findById(droneId).orElseThrow(() -> new DroneNotFoundException("Drone not registered")), DroneRegistrationResponse.class);
        return drone.getMedications();
    }

    @Override
    public List<DroneRegistrationResponse> availableDrones() {
        List<DroneRegistrationResponse> drones = droneRepository.findAll()
                .stream()
                .map(drone -> modelMapper.map(drone, DroneRegistrationResponse.class))
                .filter(d -> d.getBatteryCapacity() >= 25)
                .filter(d -> d.getWeight() > d.getMedications()
                        .stream()
                        .mapToDouble(dd -> dd.getWeight())
                        .reduce(0, (a, b) -> a + b))
                .filter(d -> d.getState() == DroneState.IDLE || d.getState() == DroneState.LOADED)
                .collect(Collectors.toList());
        return drones;
    }

    @Override
    public long batteryLevel(UUID droneId) {
        DroneRegistrationResponse drone = modelMapper.map(droneRepository.findById(droneId)
                .orElseThrow(() -> new DroneNotFoundException("No such drone is registered")), DroneRegistrationResponse.class);
        return drone.getBatteryCapacity();
    }

    @Override
    public List<DroneRegistrationResponse> allDrones() {
        List<DroneRegistrationResponse> drones = droneRepository.findAll()
                .stream()
                .map(drone -> modelMapper.map(drone, DroneRegistrationResponse.class))
                .collect(Collectors.toList());
        return drones;
    }

    @Override
    public List<BatteryLevelResponse> batteryLevels() {
        List<BatteryLevelResponse> batteryLevels = droneRepository.findAll()
                .stream()
                .map(drone -> modelMapper.map(drone, BatteryLevelResponse.class))
                .collect(Collectors.toList());
        return batteryLevels;
    }
}
