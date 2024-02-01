package com.example.bookservice.exception;

import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class BookExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseInformation> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponseInformation errorResponse = new ErrorResponseInformation(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                List.of(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();
        ErrorResponseInformation errorDetails = new ErrorResponseInformation(status.value(), new Date(), errors);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
