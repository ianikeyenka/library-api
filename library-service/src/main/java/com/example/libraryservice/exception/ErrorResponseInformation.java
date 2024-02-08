package com.example.libraryservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseInformation {
    private int statusCode;
    private Date timestamp;
    private List<String> message;
}
