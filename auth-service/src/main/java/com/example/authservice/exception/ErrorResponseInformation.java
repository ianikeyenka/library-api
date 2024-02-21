package com.example.authservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseInformation {
    private int statusCode;
    private Date timestamp;
    private String message;
}
