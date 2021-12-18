package com.musala.dronemanagement.repository;

import com.musala.dronemanagement.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DroneRepository extends JpaRepository<Drone, UUID> {
    public Drone findBySerialNumber(long serialNumber);
}
