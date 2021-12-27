package com.musala.dronemanagement.models.request;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class LoadDroneRequest {
    @NotNull(message = "Drone cannot be empty")
    private UUID droneId;

    @NotNull(message = "medication code cannot be empty")
    private String code;

    public LoadDroneRequest() {
    }

    public LoadDroneRequest(@NotNull UUID droneId, @NotNull String code) {
        this.droneId = droneId;
        this.code = code;
    }

    public UUID getDroneId() {
        return droneId;
    }

    public String getCode() {
        return code;
    }
}
