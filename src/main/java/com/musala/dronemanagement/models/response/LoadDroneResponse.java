package com.musala.dronemanagement.models.response;

import java.util.UUID;

public class LoadDroneResponse {
    private UUID droneId;
    private String name;

    public LoadDroneResponse() {
    }

    public LoadDroneResponse(UUID droneId, String name) {
        this.droneId = droneId;
        this.name = name;
    }

    public UUID getDroneId() {
        return droneId;
    }

    public void setDroneId(UUID droneId) {
        this.droneId = droneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

