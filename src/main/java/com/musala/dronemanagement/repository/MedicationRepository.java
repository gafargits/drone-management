package com.musala.dronemanagement.repository;

import com.musala.dronemanagement.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {
    public Medication findByName(String name);
}
