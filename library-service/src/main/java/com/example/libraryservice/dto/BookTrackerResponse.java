package com.example.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTrackerResponse {
    private Long id;
    private Long bookId;
    private LocalDate dateBorrowed;
    private LocalDate dateToReturn;
}
