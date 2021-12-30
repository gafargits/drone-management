package com.musala.dronemanagement.models.request;

import com.musala.dronemanagement.entities.DroneModel;
import com.musala.dronemanagement.entities.DroneState;

import javax.persistence.*;
import javax.validation.constraints.*;

public class DroneRegistrationRequest {
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Max(value = 500, message = "Max weight is 500")
    @Min(value = 0, message = "Weight cannot be negative")
    private int weight;

    @Min(value = 0, message = "Battery capacity cannot be negative")
    @Max(value = 100, message = "Battery capacity cannot exceed 100%")
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;


    public DroneRegistrationRequest() {
    }

    public DroneRegistrationRequest(
            String serialNumber, DroneModel model, @Max(value = 500, message = "Max weight is 500") @Min(value = 0, message = "Weight cannot be negative")
                                            int weight, @Min(value = 0, message = "Battery capacity cannot be negative") @Max(value = 100, message = "Battery capacity cannot exceed 100%") int batteryCapacity, DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public double getWeight() {
        return weight;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

}
