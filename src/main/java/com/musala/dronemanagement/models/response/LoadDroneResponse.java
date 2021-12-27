package com.musala.dronemanagement.models.response;

import java.util.UUID;

public class LoadDroneResponse {
    private UUID droneId;
    private String code;

    public LoadDroneResponse() {
    }

    public LoadDroneResponse(UUID droneId, String code) {
        this.droneId = droneId;
        this.code = code;
    }

    public UUID getDroneId() {
        return droneId;
    }

    public void setDroneId(UUID droneId) {
        this.droneId = droneId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

