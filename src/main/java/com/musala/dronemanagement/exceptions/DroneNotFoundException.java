package com.musala.dronemanagement.exceptions;

public class DroneNotFoundException extends RuntimeException {
    public DroneNotFoundException(String message) {
        super(message);
    }
}
