package com.otienochris.procurement_management_system.exception_handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@Slf4j
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationExceptionHandler(ConstraintViolationException e){

        log.error("in the validationExceptionHandler");
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.toString());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ObjectError>> bindingExceptionHandler(BindException exception){
        log.error("in the bindingExceptionHandler");
        return new ResponseEntity<>(exception.getAllErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> itemNotFoundExceptionHandler(IllegalArgumentException e){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .message(e.getMessage())
                .details(e.getCause().toString())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .message(e.getMessage())
                .details(e.getCause().toString())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
