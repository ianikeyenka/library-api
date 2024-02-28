package com.example.libraryservice.service;

import com.example.libraryservice.dto.BookTrackerListResponse;
import com.example.libraryservice.dto.BookTrackerRequest;
import com.example.libraryservice.dto.BookTrackerResponse;

public interface BookTrackerService {
    BookTrackerListResponse getBookTrackers();

    BookTrackerListResponse getFreeBooks();

    BookTrackerResponse updateBook(Long id, BookTrackerRequest bookTrackerRequest);

    void saveToBookTracker(Long bookId);
}
