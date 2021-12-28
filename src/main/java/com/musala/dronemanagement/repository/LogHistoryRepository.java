package com.musala.dronemanagement.repository;

import com.musala.dronemanagement.entities.LogHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LogHistoryRepository extends JpaRepository<LogHistoryEntity, UUID> {
    public List<LogHistoryEntity> findBySerialNumber(String serialNumber);
}
