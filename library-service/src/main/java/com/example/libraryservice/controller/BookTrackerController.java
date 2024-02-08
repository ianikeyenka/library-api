package com.example.libraryservice.controller;

import com.example.libraryservice.dto.BookTrackerResponse;
import com.example.libraryservice.service.BookTrackerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-trackers")
public class BookTrackerController {

    private final BookTrackerService bookTrackerService;

    @GetMapping
    public ResponseEntity<List<BookTrackerResponse>> getBookTrackers() {
        log.info("Fetching all book trackers");
        return new ResponseEntity<>(bookTrackerService.getBookTrackers(), HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<BookTrackerResponse>> getFreeBooks() {
        log.info("Fetching free books");
        return new ResponseEntity<>(bookTrackerService.getFreeBooks(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookTrackerResponse> updateBook(@PathVariable Long id, @RequestBody BookTrackerResponse bookTrackerResponse) {
        log.info("Updating book with id: {}", id);
        return new ResponseEntity<>(bookTrackerService.updateBook(id, bookTrackerResponse), HttpStatus.OK);
    }
}