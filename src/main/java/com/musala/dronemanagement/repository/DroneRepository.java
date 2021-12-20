package com.musala.dronemanagement.repository;

import com.musala.dronemanagement.entities.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<DroneEntity, UUID> {
    public DroneEntity findBySerialNumber(long serialNumber);
    public List<DroneEntity> findByState(String state);
}
