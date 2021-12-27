package com.musala.dronemanagement.models.request;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class LoadDroneRequest {
    @NotNull(message = "Drone cannot be empty")
    private UUID droneId;

    @NotNull(message = "medication code cannot be empty")
    private String medicationCode;

    public LoadDroneRequest() {
    }

    public LoadDroneRequest(@NotNull UUID droneId, @NotNull String medicationCode) {
        this.droneId = droneId;
        this.medicationCode = medicationCode;
    }

    public UUID getDroneId() {
        return droneId;
    }

    public String getMedicationCode() {
        return medicationCode;
    }
}
