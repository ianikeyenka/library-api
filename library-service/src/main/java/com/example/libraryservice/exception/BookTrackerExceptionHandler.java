package com.example.libraryservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;

@Slf4j
@ControllerAdvice
public class BookTrackerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseInformation> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponseInformation errorResponse = new ErrorResponseInformation(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                List.of(ex.getMessage()));
        log.error("Resource not found: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
