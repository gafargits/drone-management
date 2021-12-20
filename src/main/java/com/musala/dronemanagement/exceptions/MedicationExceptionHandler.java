package com.musala.dronemanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class MedicationExceptionHandler {

    @ExceptionHandler(value = {MedicationRequestException.class})
    public ResponseEntity<Object> handleMedicationRequestException(MedicationRequestException e){
        MedicationException medicationException =  new MedicationException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(medicationException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {MedicationNotFoundException.class})
    public ResponseEntity<Object> handleMedicationNotFoundException(MedicationNotFoundException e){
        MedicationException medicationException =  new MedicationException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(medicationException, HttpStatus.NOT_FOUND);
    }
}
