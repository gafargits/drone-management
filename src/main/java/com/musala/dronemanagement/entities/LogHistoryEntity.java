package com.musala.dronemanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class LogHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String serialNumber;
    private int batteryCapacity;
    private ZonedDateTime timeStamp;

    public LogHistoryEntity() {
        this.timeStamp = ZonedDateTime.now(ZoneId.of("Z"));
    }

    public UUID getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
