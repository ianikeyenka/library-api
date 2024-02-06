package com.example.bookservice.controller;


import com.example.bookservice.dto.BookResponse;
import com.example.bookservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        log.info("Fetching all books");
        List<BookResponse> bookResponses = bookService.getBooks();
        return new ResponseEntity<>(bookResponses, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        log.info("Fetching book with id: {}", id);
        BookResponse bookResponse = bookService.getBookById(id);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponse> getBookByISBN(@PathVariable String isbn) {
        log.info("Fetching book with ISBN: {}", isbn);
        BookResponse bookResponse = bookService.getBookByISBN(isbn);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponse> saveBook(@Valid @RequestBody BookResponse bookResponse) {
        log.info("Saving new book: {}", bookResponse);
        bookService.addBook(bookResponse);
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookResponse bookResponse) {
        log.info("Updating book with id: {}", id);
        bookService.updateBook(id, bookResponse);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("Deleting book with id: {}", id);
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
