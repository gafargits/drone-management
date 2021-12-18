package com.musala.dronemanagement.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "medicationWeight")
    private double weight;

    @Column(name = "medicationCode")
    private String code;

    @Column(name = "medicationImage", length = 64)
    private String medicationImage;

    public Medication() {
    }

    public Medication(UUID id, double weight, String code, String medicationImage) {
        this.id = id;
        this.weight = weight;
        this.code = code;
        this.medicationImage = medicationImage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMedicationImage() {
        return medicationImage;
    }

    public void setMedicationImage(String medicationImage) {
        this.medicationImage = medicationImage;
    }
}
