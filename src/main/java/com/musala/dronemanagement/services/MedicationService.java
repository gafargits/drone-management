package com.musala.dronemanagement.services;

import com.musala.dronemanagement.models.request.MedicationRequest;
import com.musala.dronemanagement.models.response.MedicationResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public interface MedicationService {
    List<MedicationResponse> registeredMedications();
    MedicationResponse getMedication(UUID medicationId);
    MedicationResponse registerMedication(MedicationRequest medicationRequest);
}
