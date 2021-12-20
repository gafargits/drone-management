package com.musala.dronemanagement.models.request;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class LoadDroneRequest {
    @NotNull(message = "Drone cannot be empty")
    private UUID droneId;

    @NotNull(message = "medication name cannot be empty")
    private String name;

    public LoadDroneRequest() {
    }

    public LoadDroneRequest(@NotNull UUID droneId, @NotNull String name) {
        this.droneId = droneId;
        this.name = name;
    }

    public UUID getDroneId() {
        return droneId;
    }

    public String getName() {
        return name;
    }
}
