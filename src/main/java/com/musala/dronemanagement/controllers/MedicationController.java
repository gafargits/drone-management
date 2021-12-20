package com.musala.dronemanagement.controllers;

import com.musala.dronemanagement.models.request.DroneRegistrationRequest;
import com.musala.dronemanagement.models.request.MedicationRequest;
import com.musala.dronemanagement.models.response.DroneRegistrationResponse;
import com.musala.dronemanagement.models.response.MedicationResponse;
import com.musala.dronemanagement.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medication")
@Validated
public class MedicationController {
    public final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping("/register")
    public ResponseEntity<MedicationResponse> registerMedication(@RequestBody @Valid final MedicationRequest medicationRequest){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/medication/register").toUriString());
        return ResponseEntity.created(uri).body(medicationService.registerMedication(medicationRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationResponse> getMedication(@PathVariable UUID id){
        return ResponseEntity.ok().body(medicationService.getMedication(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicationResponse>> allMedications(){
        return ResponseEntity.ok().body(medicationService.registeredMedications());
    }
}
