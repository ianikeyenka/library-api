package com.example.libraryservice.service;

import com.example.libraryservice.dto.BookTrackerResponse;

import java.util.List;

public interface BookTrackerService {
    List<BookTrackerResponse> getBookTrackers();

    List<BookTrackerResponse> getFreeBooks();

    BookTrackerResponse saveToBookTracker(Long bookId);
}
