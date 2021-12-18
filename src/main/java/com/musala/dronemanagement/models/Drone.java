package com.musala.dronemanagement.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 100, name = "serialNumber")
    private long serialNumber;

    @Column(name = "droneModel")
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column(name = "droneWeight")
    @Max(value = 500, message = "Max weight is 500")
    @Min(value = 0, message = "Weight cannot be negative")
    private double weight;

    @Min(value = 0, message = "Battery capacity cannot be negative")
    @Max(value = 100, message = "Battery capacity cannot exceed 100%")
    private int batteryCapacity;

    @Column(name = "droneState")
    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Medication> medications = new ArrayList<>();

    public Drone() {
    }

    public Drone(UUID id, long serialNumber, DroneModel model, @Max(value = 500, message = "Max weight is 500") @Min(value = 0, message = "Weight cannot be negative") double weight, @Min(value = 0, message = "Battery capacity cannot be negative") @Max(value = 100, message = "Battery capacity cannot exceed 100%") int batteryCapacity, DroneState state) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
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

    public double getBatteryCapacity() {
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
}
