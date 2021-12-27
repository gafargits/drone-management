package com.musala.dronemanagement.controllers;

import com.musala.dronemanagement.exceptions.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
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

import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        DroneException droneException = new DroneException(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(droneException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, List<String>> onConstraintValidationException(
            ConstraintViolationException e) {
        Map<String, List<String>> body = new HashMap<>();
        body.put("errors", e.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList()));
        return body;
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request)  {

        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {DroneRequestException.class})
    public ResponseEntity<Object> handleDroneRequestException(DroneRequestException e) {
        //Create payload containing exception details
        DroneException droneException = new DroneException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //Return response entity
        return new ResponseEntity<>(droneException, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {DroneNotFoundException.class})
    public ResponseEntity<Object> droneNotFoundException(DroneNotFoundException e) {
        DroneException droneException = new DroneException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(droneException, HttpStatus.NOT_FOUND);
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

//    @ExceptionHandler({DataIntegrityViolationException.class})
//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ResponseBody
//    public ValidationErrorResponse onDataIntegrityViolationException(DataIntegrityViolationException e) {
//        String message = e.getMostSpecificCause().getMessage();
//        String table = StringUtils.substringBetween(message, "tbl_", "_col");
//        String columns = StringUtils.substringBetween(message, "_col_", "\"");
//
//        String resource = table == null ? "resource" : table;
//        String fields = columns == null ? "fields" : columns.replace("__", ", ").replace("_", " ");
//
//        ValidationErrorResponse error = new ValidationErrorResponse();
//
//        error.setErrors(singletonList(new Violation(fields, format("%s with matching %s already exists",
//                WordUtils.capitalize(resource.replace("_", " ")), fields))
//        ));
//        return error;
//    }
}
