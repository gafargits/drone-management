package com.musala.dronemanagement.services;

import com.musala.dronemanagement.entities.MedicationEntity;
import com.musala.dronemanagement.exceptions.MedicationRequestException;
import com.musala.dronemanagement.models.request.MedicationRequest;
import com.musala.dronemanagement.models.response.DroneRegistrationResponse;
import com.musala.dronemanagement.models.response.MedicationResponse;
import com.musala.dronemanagement.repository.MedicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;
    private final ModelMapper modelMapper;

    public MedicationServiceImpl(MedicationRepository medicationRepository, ModelMapper modelMapper) {
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MedicationResponse> registeredMedications() {
        List<MedicationResponse> medications = medicationRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, MedicationResponse.class))
                .collect(Collectors.toList());
        return medications;
    }

    @Override
    public MedicationResponse getMedication(UUID medicationId) {
        MedicationResponse medication = modelMapper.map(medicationRepository.findById(medicationId).orElseThrow(() -> new MedicationRequestException("No such medication")), MedicationResponse.class);
        return medication;
    }

    @Override
    public MedicationResponse registerMedication(MedicationRequest medicationRequest) {
        MedicationEntity medicationEntity = modelMapper.map(medicationRequest, MedicationEntity.class);
        MedicationEntity existing = medicationRepository.findByCode(medicationEntity.getCode());
        if(existing == null){
            return modelMapper.map(medicationRepository.save(medicationEntity), MedicationResponse.class);
        }
        throw new MedicationRequestException("Medication already registered. You can only register a medication once.");

    }
}
