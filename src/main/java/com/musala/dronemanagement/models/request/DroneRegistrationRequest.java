package com.musala.dronemanagement.models.request;

import com.musala.dronemanagement.entities.DroneModel;
import com.musala.dronemanagement.entities.DroneState;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class DroneRegistrationRequest {
    @NotNull
    private long serialNumber;

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

    private List<MedicationRequest> medications = new ArrayList<>();

    public DroneRegistrationRequest() {
    }

    public DroneRegistrationRequest(@NotNull @Pattern(regexp = "[0-9]+", message = "serial number can only be number") long serialNumber, DroneModel model, @Max(value = 500, message = "Max weight is 500") @Min(value = 0, message = "Weight cannot be negative") int weight, @Min(value = 0, message = "Battery capacity cannot be negative") @Max(value = 100, message = "Battery capacity cannot exceed 100%") int batteryCapacity, DroneState state, List<MedicationRequest> medications) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.medications = medications;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public double getWeight() {
        return weight;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

}
