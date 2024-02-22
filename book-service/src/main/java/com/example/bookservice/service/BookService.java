package com.example.bookservice.service;


import com.example.bookservice.dto.BookListResponse;
import com.example.bookservice.dto.BookRequest;
import com.example.bookservice.dto.BookResponse;

public interface BookService {
    BookListResponse getBooks();

    BookResponse getBookById(Long id);

    BookResponse getBookByISBN(String isbn);

    BookResponse saveBook(BookRequest bookRequest);

    BookResponse updateBook(Long id, BookRequest bookRequest);

    BookResponse deleteBook(Long id);
}
