package com.musala.dronemanagement.exceptions;

public class MedicationNotFoundException extends RuntimeException {
    public MedicationNotFoundException(String message) {
        super(message);
    }
}
