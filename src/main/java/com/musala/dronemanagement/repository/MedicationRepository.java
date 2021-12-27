package com.musala.dronemanagement.repository;

import com.musala.dronemanagement.entities.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationEntity, UUID> {
    public MedicationEntity findByCode(String name);
}
