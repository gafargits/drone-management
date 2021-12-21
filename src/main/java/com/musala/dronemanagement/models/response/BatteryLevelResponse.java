package com.musala.dronemanagement.models.response;

public class BatteryLevelResponse {
    private long serialNumber;
    private int batteryCapacity;

    public BatteryLevelResponse() {
    }

    public BatteryLevelResponse(long serialNumber, int batteryCapacity) {
        this.serialNumber = serialNumber;
        this.batteryCapacity = batteryCapacity;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
