package com.musala.dronemanagement.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "serialNumber", name = "uq_tbl_drone_entity_col_name")
})
public class DroneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Max(value = 500, message = "Max weight is 500")
    @Min(value = 0, message = "Weight cannot be negative")
    private double weight;

    @Min(value = 0, message = "Battery capacity cannot be negative")
    @Max(value = 100, message = "Battery capacity cannot exceed 100%")
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<MedicationEntity> medications = new ArrayList<>();

    public DroneEntity() {
    }

    public DroneEntity(UUID id, String serialNumber, DroneModel model, @Max(value = 500, message = "Max weight is 500") @Min(value = 0, message = "Weight cannot be negative") double weight, @Min(value = 0, message = "Battery capacity cannot be negative") @Max(value = 100, message = "Battery capacity cannot exceed 100%") int batteryCapacity, DroneState state, List<MedicationEntity> medications) {
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
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

    public List<MedicationEntity> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationEntity> medications) {
        this.medications = medications;
    }
}
