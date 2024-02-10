package com.example.bookservice.service;


import com.example.bookservice.dto.BookListResponse;
import com.example.bookservice.dto.BookResponse;

import java.util.List;

public interface BookService {
    BookListResponse getBooks();

    BookResponse getBookById(Long id);

    BookResponse getBookByISBN(String isbn);

    BookResponse saveBook(BookResponse bookResponse);

    BookResponse updateBook(Long id, BookResponse bookResponse);

    BookResponse deleteBook(Long id);
}
