package com.musala.dronemanagement.models.response;

import java.util.UUID;

public class LoadDroneResponse {
    private UUID droneId;
    private String medicationCode;

    public LoadDroneResponse() {
    }

    public LoadDroneResponse(UUID droneId, String medicationCode) {
        this.droneId = droneId;
        this.medicationCode = medicationCode;
    }

    public UUID getDroneId() {
        return droneId;
    }

    public void setDroneId(UUID droneId) {
        this.droneId = droneId;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }
}

