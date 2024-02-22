package com.example.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookTrackerListResponse {
    private List<BookTrackerResponse> bookTrackerRequestList;
}
