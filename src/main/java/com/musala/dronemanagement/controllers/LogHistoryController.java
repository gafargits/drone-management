package com.musala.dronemanagement.controllers;

import com.musala.dronemanagement.models.response.BatteryLevelResponse;
import com.musala.dronemanagement.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LogHistoryController {
    private final DroneService droneService;

    @Autowired
    public LogHistoryController(DroneService droneService) {
        this.droneService = droneService;
    }

    @Scheduled(fixedRate = 1800000)
    @PostMapping("/log-drone-battery-levels")
    public ResponseEntity<Void> logBatteryLevels(){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/log-drone-battery-levels").toUriString());
        droneService.logBatteryLevels();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/get-drone-battery-levels")
    public ResponseEntity<List<BatteryLevelResponse>> getBatteryLevels(){
        return ResponseEntity.ok().body(droneService.getBatteryLevels());
    }
}
