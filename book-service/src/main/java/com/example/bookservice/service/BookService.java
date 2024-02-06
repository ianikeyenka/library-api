package com.example.bookservice.service;


import com.example.bookservice.dto.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> getBooks();

    BookResponse getBookById(Long id);

    BookResponse getBookByISBN(String isbn);

    void addBook(BookResponse bookDTO);

    void updateBook(Long id, BookResponse bookDTO);

    void deleteBook(Long id);
}
