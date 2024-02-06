package com.example.bookservice.service;


import com.example.bookservice.dto.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> getBooks();

    BookResponse getBookById(Long id);

    BookResponse getBookByISBN(String isbn);

    void saveBook(BookResponse bookResponse);

    void updateBook(Long id, BookResponse bookResponse);

    void deleteBook(Long id);
}
