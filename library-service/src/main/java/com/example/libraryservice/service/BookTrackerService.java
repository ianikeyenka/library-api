package com.example.libraryservice.service;

import com.example.libraryservice.dto.BookTrackerListResponse;
import com.example.libraryservice.dto.BookTrackerResponse;

import java.util.List;

public interface BookTrackerService {
    BookTrackerListResponse getBookTrackers();

    List<BookTrackerResponse> getFreeBooks();

    BookTrackerResponse updateBook(Long id, BookTrackerResponse bookTrackerResponse);

    void saveToBookTracker(Long bookId);
}
