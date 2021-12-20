package com.musala.dronemanagement.models.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MedicationRequest {
    @Pattern(regexp = "[A-Za-z0-9_-]+", message = "medication name can only be letters, numbers, _, -")
    @NotNull(message = "Medication name cannot be empty")
    private String name;

    @Min(value = 0, message = "Medication must have a specified weight greater than 0")
    @Max(value = 500, message = "Medication greater than 500 cannot be transported with our drones")
    private double weight;

    @Pattern(regexp = "[A-Z0-9_]+", message = "Code can only be capital letters, numbers and _")
    @NotNull(message = "medication code cannot be empty")
    private String code;

    private String medicationImageUrl;

    public MedicationRequest() {
    }

    public MedicationRequest(@Pattern(regexp = "[A-Za-z0-9_-]+", message = "medication name can only be letters, numbers, _, -") @NotNull(message = "Medication name cannot be empty") String name, @Min(value = 0, message = "Medication must have a specified weight greater than 0") @Max(value = 500, message = "Medication greater than 500 cannot be transported with our drones") double weight, @Pattern(regexp = "[A-Z0-9_]+", message = "Code can only be capital letters, numbers and _") @NotNull(message = "medication code cannot be empty") String code, String medicationImageUrl) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.medicationImageUrl = medicationImageUrl;
    }

    public double getWeight() {
        return weight;
    }

    public String getCode() {
        return code;
    }

    public String getMedicationImageUrl() {
        return medicationImageUrl;
    }

    public String getName() {
        return name;
    }


}
