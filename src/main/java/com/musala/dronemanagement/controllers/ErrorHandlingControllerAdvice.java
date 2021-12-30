package com.musala.dronemanagement.controllers;

import com.musala.dronemanagement.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ErrorHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        APISubError APISubError = new APISubError(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(APISubError, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request)  {

        APISubError APISubError = new APISubError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(APISubError, HttpStatus.BAD_REQUEST);
//        Map<String, List<String>> body = new HashMap<>();
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .map(f -> f.getField() +  " has invalid value supplied" )
//                .collect(Collectors.toList());
//
//        body.put("errors", errors);
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {DroneRequestException.class})
    public ResponseEntity<Object> handleDroneRequestException(DroneRequestException e) {
        APISubError APISubError = new APISubError(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(APISubError, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {DroneNotFoundException.class})
    public ResponseEntity<Object> droneNotFoundException(DroneNotFoundException e) {
        APISubError APISubError = new APISubError(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(APISubError, HttpStatus.NOT_FOUND);
    }

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
