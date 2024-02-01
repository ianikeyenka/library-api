package com.example.bookservice.service;

import com.example.bookservice.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks();

    BookDTO getBookById(Long id);

    BookDTO getBookByISBN(String isbn);

    void addBook(BookDTO bookDTO);

    void updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);
}
