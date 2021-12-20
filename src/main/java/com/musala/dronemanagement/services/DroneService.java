package com.musala.dronemanagement.services;

import com.musala.dronemanagement.entities.DroneEntity;
import com.musala.dronemanagement.models.request.DroneRegistrationRequest;
import com.musala.dronemanagement.models.request.LoadDroneRequest;
import com.musala.dronemanagement.models.response.DroneRegistrationResponse;
import com.musala.dronemanagement.models.response.LoadDroneResponse;
import com.musala.dronemanagement.models.response.MedicationResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface DroneService {
    DroneRegistrationResponse registerDrone(DroneRegistrationRequest drone);
    LoadDroneResponse loadDrone(LoadDroneRequest loadDroneRequest);
    List<MedicationResponse> checkMedicationLoaded(UUID droneId);
    List<DroneRegistrationResponse> availableDrones();
    long batteryLevel(UUID droneId);
    List<DroneRegistrationResponse> allDrones();
}
