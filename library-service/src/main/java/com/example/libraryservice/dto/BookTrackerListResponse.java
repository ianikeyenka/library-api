package com.example.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTrackerListResponse {
    private List<BookTrackerResponse> bookTrackerResponseList;
}
