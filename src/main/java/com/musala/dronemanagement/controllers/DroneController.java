package com.musala.dronemanagement.controllers;

import com.musala.dronemanagement.exceptions.DroneRequestException;
import com.musala.dronemanagement.models.request.DroneRegistrationRequest;
import com.musala.dronemanagement.models.request.LoadDroneRequest;
import com.musala.dronemanagement.models.response.DroneRegistrationResponse;
import com.musala.dronemanagement.models.response.LoadDroneResponse;
import com.musala.dronemanagement.models.response.MedicationResponse;
import com.musala.dronemanagement.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drone")
@Validated
public class DroneController {

    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<DroneRegistrationResponse> registerDrone(@RequestBody @Valid final DroneRegistrationRequest drone){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/drone/register").toUriString());
        return ResponseEntity.created(uri).body(droneService.registerDrone(drone));
    }

    @PostMapping("/load")
    public ResponseEntity<LoadDroneResponse> loadDrone(@RequestBody @Valid final LoadDroneRequest loadDroneRequest){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/drone/load").toUriString());
        return ResponseEntity.created(uri).body(droneService.loadDrone(loadDroneRequest));
    }

    @GetMapping("/loaded-medications/{id}")
    public ResponseEntity<List<MedicationResponse>> loadedMedications(@PathVariable final UUID id){
        return ResponseEntity.ok().body(droneService.checkMedicationLoaded(id));
    }

    @GetMapping("/available-drones")
    public ResponseEntity<List<DroneRegistrationResponse>> avaialableDrones(){
        return ResponseEntity.ok().body(droneService.availableDrones());
    }

    @GetMapping("/all-drones")
    public ResponseEntity<List<DroneRegistrationResponse>> allDrones(){
        return ResponseEntity.ok().body(droneService.allDrones());
    }

    @GetMapping("/battery-level/{id}")
    public ResponseEntity<Long> droneBatteryLevel(@PathVariable final UUID id){
        return ResponseEntity.ok().body(droneService.batteryLevel(id));
    }
}
