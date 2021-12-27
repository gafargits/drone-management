package com.musala.dronemanagement.config;

import com.musala.dronemanagement.entities.DroneModel;
import com.musala.dronemanagement.entities.DroneState;
import com.musala.dronemanagement.models.request.DroneRegistrationRequest;
import com.musala.dronemanagement.models.request.MedicationRequest;
import com.musala.dronemanagement.services.DroneServiceImpl;
import com.musala.dronemanagement.services.MedicationServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PreloadDB {

    private List<MedicationRequest> medicationEntities;

    @Bean
    CommandLineRunner run(DroneServiceImpl droneService, MedicationServiceImpl medicationService){
		return args -> {
            droneService.registerDrone(new DroneRegistrationRequest(12345, DroneModel.Lightweight, 100, 70, DroneState.IDLE ));
			droneService.registerDrone(new DroneRegistrationRequest(12346, DroneModel.Cruiserweight, 250, 50, DroneState.IDLE ));
            droneService.registerDrone(new DroneRegistrationRequest(12347, DroneModel.Heavyweight, 500, 80, DroneState.IDLE ));

            medicationService.registerMedication(new MedicationRequest("Paracetamol", 20, "PARA", "http://paracetamol.url"));
            medicationService.registerMedication(new MedicationRequest("Piriton", 25, "PIRI", "http://piriton.url"));
            medicationService.registerMedication(new MedicationRequest("Tetracycline", 30, "TETR", "http://tetracycline.url"));
		};
	}
}
