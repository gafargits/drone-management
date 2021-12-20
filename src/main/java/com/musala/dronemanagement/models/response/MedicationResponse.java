package com.musala.dronemanagement.models.response;

import java.util.UUID;

public class MedicationResponse {
    private UUID id;
    private String name;
    private double weight;
    private String code;
    private String medicationImageUrl;

    public MedicationResponse() {
    }

    public MedicationResponse(UUID id, String name, double weight, String code, String medicationImageUrl) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMedicationImageUrl() {
        return medicationImageUrl;
    }

    public void setMedicationImageUrl(String medicationImageUrl) {
        this.medicationImageUrl = medicationImageUrl;
    }
}
