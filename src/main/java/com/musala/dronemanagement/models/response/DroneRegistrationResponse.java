package com.musala.dronemanagement.models.response;

import com.musala.dronemanagement.entities.DroneModel;
import com.musala.dronemanagement.entities.DroneState;
import com.musala.dronemanagement.models.request.MedicationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DroneRegistrationResponse {
    private UUID id;
    private long serialNumber;
    private DroneModel model;
    private double weight;
    private int batteryCapacity;
    private DroneState state;
    private List<MedicationResponse> medications = new ArrayList<>();

    public DroneRegistrationResponse() {
    }

    public DroneRegistrationResponse(UUID id, long serialNumber, DroneModel model, double weight, int batteryCapacity, DroneState state, List<MedicationResponse> medications) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.medications = medications;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    public List<MedicationResponse> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationResponse> medications) {
        this.medications = medications;
    }
}
