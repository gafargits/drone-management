package com.musala.dronemanagement.services;

import com.musala.dronemanagement.entities.DroneEntity;
import com.musala.dronemanagement.entities.DroneModel;
import com.musala.dronemanagement.entities.DroneState;
import com.musala.dronemanagement.entities.MedicationEntity;
import com.musala.dronemanagement.models.request.DroneRegistrationRequest;
import com.musala.dronemanagement.models.request.LoadDroneRequest;
import com.musala.dronemanagement.models.request.MedicationRequest;
import com.musala.dronemanagement.models.response.DroneRegistrationResponse;
import com.musala.dronemanagement.models.response.LoadDroneResponse;
import com.musala.dronemanagement.models.response.MedicationResponse;

import java.util.ArrayList;
import java.util.UUID;

public class BaseServiceTest {

    DroneRegistrationRequest getDroneRegistrationRequest(){
        return new DroneRegistrationRequest(1L, DroneModel.Cruiserweight, 250, 50, DroneState.IDLE);
    }

    DroneRegistrationRequest getDroneRegistrationRequest(long serialNumber){
        return new DroneRegistrationRequest(serialNumber, DroneModel.Cruiserweight, 250, 50, DroneState.IDLE);
    }
    DroneEntity getDroneEntity(long serialNumber){
        var drone = getDroneRegistrationRequest(serialNumber);
        return new DroneEntity(UUID.randomUUID(), drone.getSerialNumber(), drone.getModel(), drone.getWeight(), drone.getBatteryCapacity(), drone.getState(), new ArrayList<>() );
    }
    DroneEntity getDroneEntity(long serialNumber, double weight){
        var response = getDroneEntity(serialNumber);
        response.setWeight(weight);
        return response;
    }
    DroneEntity getDroneEntityWithCapacity(long serialNumber, int batteryCapacity){
        var response = getDroneEntity(serialNumber);
        response.setBatteryCapacity(batteryCapacity);
        return response;
    }

    DroneEntity getDroneEntityWithDroneState(long serialNumber, DroneState droneState){
        var response = getDroneEntity(serialNumber);
        response.setState(droneState);
        return response;
    }

    DroneRegistrationResponse getDroneResponse(long serialNumber){
        var drone = getDroneRegistrationRequest(serialNumber);
        return new DroneRegistrationResponse(UUID.randomUUID(), drone.getSerialNumber(), drone.getModel(), drone.getWeight(), drone.getBatteryCapacity(), drone.getState(), new ArrayList<>() );
    }

    DroneRegistrationResponse getDroneResponse(long serialNumber, int batteryCapacity){
        var drone = getDroneRegistrationRequest(serialNumber);
        var regResponse = new DroneRegistrationResponse(UUID.randomUUID(), drone.getSerialNumber(), drone.getModel(), drone.getWeight(), drone.getBatteryCapacity(), drone.getState(), new ArrayList<>() );
        regResponse.setBatteryCapacity(batteryCapacity);
        return regResponse;
    }

    DroneRegistrationResponse getDroneResponse(long serialNumber, DroneState droneState){
        var drone = getDroneRegistrationRequest(serialNumber);
        var regResponse = new DroneRegistrationResponse(UUID.randomUUID(), drone.getSerialNumber(), drone.getModel(), drone.getWeight(), drone.getBatteryCapacity(), drone.getState(), new ArrayList<>() );
        regResponse.setState(droneState);
        return regResponse;
    }

    DroneRegistrationResponse getDroneResponse(UUID id){
        var drone = getDroneRegistrationRequest();
        return new DroneRegistrationResponse(id, drone.getSerialNumber(), drone.getModel(), drone.getWeight(), drone.getBatteryCapacity(), drone.getState(), new ArrayList<>() );
    }

    DroneEntity getDroneEntity(UUID id){
        var drone = getDroneRegistrationRequest();
        return new DroneEntity(id, drone.getSerialNumber(), drone.getModel(), drone.getWeight(), drone.getBatteryCapacity(), drone.getState(), new ArrayList<>() );
    }

    MedicationRequest getMedicationRequest(){
        return new MedicationRequest("Paracetamol", 20, "PARA", "http://medication.com");
    }

    MedicationRequest getMedicationRequest(String code){
        return new MedicationRequest("Paracetamol", 20, code, "http://medication.com");
    }

    MedicationEntity getMedicationEntity(String code){
        var medication = getMedicationRequest(code);
        return new MedicationEntity(UUID.randomUUID(), medication.getName(), medication.getWeight(), code, medication.getMedicationImageUrl());
    }
    MedicationEntity getMedicationEntity(){
        var medication = getMedicationRequest();
        return new MedicationEntity(UUID.randomUUID(), medication.getName(), medication.getWeight(), medication.getCode(), medication.getMedicationImageUrl());
    }

    MedicationResponse getMedicationResponse(String code){
        var medication = getMedicationRequest(code);
        return new MedicationResponse(UUID.randomUUID(), medication.getName(), medication.getWeight(), code, medication.getMedicationImageUrl());
    }

    MedicationResponse getMedicationResponse(){
        var medication = getMedicationRequest();
        return new MedicationResponse(UUID.randomUUID(), medication.getName(), medication.getWeight(), medication.getCode(), medication.getMedicationImageUrl());
    }

    LoadDroneRequest getLoadDroneRequest(UUID id, String code){
        return new LoadDroneRequest(id, code);
    }

    LoadDroneResponse getLoadDroneResponse(UUID id, String code){
        var loadDroneRequest = getLoadDroneRequest(id, code);
        return new LoadDroneResponse(loadDroneRequest.getDroneId(), loadDroneRequest.getCode());
    }
}
