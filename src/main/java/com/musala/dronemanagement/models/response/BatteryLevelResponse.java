package com.musala.dronemanagement.models.response;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BatteryLevelResponse {
    private String serialNumber;
    private int batteryCapacity;
    private ZonedDateTime timeStamp;

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
