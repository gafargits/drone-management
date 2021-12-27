package com.musala.dronemanagement.services;

import com.musala.dronemanagement.entities.DroneEntity;
import com.musala.dronemanagement.entities.DroneState;
import com.musala.dronemanagement.exceptions.DroneRequestException;
import com.musala.dronemanagement.models.response.DroneRegistrationResponse;
import com.musala.dronemanagement.repository.DroneRepository;
import com.musala.dronemanagement.repository.MedicationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest extends BaseServiceTest {

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DroneServiceImpl droneServiceImpl;

    @Test
    @DisplayName("register_drone_successful")
    void registerDrone_successful() {
        //Given
        long serialNumber = 1L;
        var droneEntity = getDroneEntity(serialNumber);
        var droneRequest = getDroneRegistrationRequest(serialNumber);
        var expectedResponse = getDroneResponse(serialNumber);
        //When
        when(droneRepository.findBySerialNumber(anyLong())).thenReturn(null);
        when(droneRepository.save(droneEntity)).thenReturn(droneEntity);
        when(modelMapper.map(droneRequest, DroneEntity.class)).thenReturn(droneEntity);
        when(modelMapper.map(droneEntity, DroneRegistrationResponse.class)).thenReturn(expectedResponse);

        var response = droneServiceImpl.registerDrone(droneRequest);
        //Then
        assertNotNull(response);
        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    @Test
    void registerDrone_droneAlreadExist_throwsException() {
        //Given
        long serialNumber = 1L;
        var droneEntity = getDroneEntity(serialNumber);
        var droneRequest = getDroneRegistrationRequest(serialNumber);
        //When
        when(droneRepository.findBySerialNumber(anyLong())).thenReturn(droneEntity); //drone already exist in DB
        when(modelMapper.map(droneRequest, DroneEntity.class)).thenReturn(droneEntity);

        //Then
        assertThrows(DroneRequestException.class, () -> droneServiceImpl.registerDrone(droneRequest));
        verify(droneRepository, times(1)).findBySerialNumber(serialNumber);
    }


    @Test
    void loadDrone_successful() {
        //Given
        UUID id = UUID.randomUUID();
        String code = "PARA";
        var droneEntity = getDroneEntity(id);
        var expectedResponse = getDroneResponse(id);
        var medicationEntity = getMedicationEntity();
        var loadDroneRequest = getLoadDroneRequest(id, code);

        //When
        when(droneRepository.findById(id)).thenReturn(ofNullable(droneEntity));
        when(droneRepository.saveAndFlush(droneEntity)).thenReturn(droneEntity);
        when(medicationRepository.findByCode(code)).thenReturn(medicationEntity);
        when(modelMapper.map(droneEntity,DroneRegistrationResponse.class)).thenReturn(expectedResponse);
        when(modelMapper.map(expectedResponse,DroneEntity.class)).thenReturn(droneEntity);

        var response = droneServiceImpl.loadDrone(loadDroneRequest);

        //Then
        assertNull(response);
        verify(droneRepository, times(1)).findById(id);
        verify(medicationRepository, times(1)).findByCode(code);
    }

    @Test
    void loadDrone_fail_batteryLessThan25(){
        //Given
        UUID id = UUID.randomUUID();
        int batteryCapacity = 24;
        var droneEntity = getDroneEntityWithCapacity(1L, batteryCapacity);
        var loadDroneRequest = getLoadDroneRequest(id, "");
        var droneResponse = getDroneResponse(1L, batteryCapacity);

        //When
        when(droneRepository.findById(id)).thenReturn(ofNullable(droneEntity));
        when(modelMapper.map(droneEntity, DroneRegistrationResponse.class)).thenReturn(droneResponse);

        //Then
        assertThrows(DroneRequestException.class, () -> droneServiceImpl.loadDrone(loadDroneRequest));
        verify(droneRepository, times(1)).findById(id);

    }

    @Test
    void loadDrone_fail_droneStateLoading(){
        //Given
        UUID id = UUID.randomUUID();
        DroneState droneState = DroneState.LOADING;
        var droneEntity = getDroneEntityWithDroneState(1L, droneState);
        var loadDroneRequest = getLoadDroneRequest(id, "");
        var droneResponse = getDroneResponse(1L, droneState);

        //When
        when(droneRepository.findById(id)).thenReturn(ofNullable(droneEntity));
        when(modelMapper.map(droneEntity, DroneRegistrationResponse.class)).thenReturn(droneResponse);

        //Then
        assertThrows(DroneRequestException.class, () -> droneServiceImpl.loadDrone(loadDroneRequest));
        verify(droneRepository, times(1)).findById(id);

    }

}