package com.musala.dronemanagement.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class MedicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column(name = "medicationWeight")
    private double weight;

    @Column(name = "medicationCode")
    private String code;

    @Column(name = "medicationImageUrl")
    private String medicationImageUrl;


    public MedicationEntity() {
    }

    public MedicationEntity(UUID id, String name, double weight, String code, String medicationImageUrl) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.medicationImageUrl = medicationImageUrl;
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
        return medicationImageUrl;
    }

    public void setMedicationImage(String medicationImageUrl) {
        this.medicationImageUrl = medicationImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
