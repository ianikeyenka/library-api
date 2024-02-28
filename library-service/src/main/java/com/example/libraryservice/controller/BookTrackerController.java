package com.example.libraryservice.controller;

import com.example.libraryservice.dto.BookTrackerListResponse;
import com.example.libraryservice.dto.BookTrackerRequest;
import com.example.libraryservice.dto.BookTrackerResponse;
import com.example.libraryservice.service.BookTrackerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-trackers")
public class BookTrackerController {

    private final BookTrackerService bookTrackerService;

    @GetMapping
    public ResponseEntity<BookTrackerListResponse> getBookTrackers() {
        log.info("Fetching all book trackers");
        return ResponseEntity.ok(bookTrackerService.getBookTrackers());
    }

    @GetMapping("/free")
    public ResponseEntity<BookTrackerListResponse> getFreeBooks() {
        log.info("Fetching free books");
        return ResponseEntity.ok(bookTrackerService.getFreeBooks());
    }

    @PostMapping("/{id}")
    public ResponseEntity<BookTrackerResponse> updateBook(@PathVariable Long id, @RequestBody BookTrackerRequest bookTrackerRequest) {
        log.info("Updating book with id: {}", id);
        return ResponseEntity.ok(bookTrackerService.updateBook(id, bookTrackerRequest));
    }
}
