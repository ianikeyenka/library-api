package com.example.bookservice.service.impl;

import com.example.bookservice.dto.BookResponse;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bookservice.util.Constant.BOOK_NOT_FOUND_BY_ID;
import static com.example.bookservice.util.Constant.BOOK_NOT_FOUND_BY_ISBN;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookResponse> getBooks() {
        log.info("Fetching all books");
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {
        log.info("Fetching book with id: {}", id);
        return bookMapper.bookToBookDto(getBookByIdOrThrow(id));
    }

    @Override
    public BookResponse getBookByISBN(String isbn) {
        log.info("Fetching book with ISBN: {}", isbn);
        Book bookModel = getBookByIsbnOrThrow(isbn);
        return bookMapper.bookToBookDto(bookModel);
    }

    @Override
    public void saveBook(BookResponse bookResponse) {
        log.info("Adding new book: {}", bookResponse);
        bookRepository.save(bookMapper.bookDtoToBook(bookResponse));
    }

    @Override
    public void updateBook(Long id, BookResponse bookResponse) {
        log.info("Updating book with id: {}", id);
        BookResponse existingBook = bookMapper
                .bookToBookDto(getBookByIdOrThrow(id));
        updateExistingBook(existingBook, bookResponse);
        bookRepository.save(bookMapper.bookDtoToBook(existingBook));
    }

    @Override
    public BookResponse deleteBook(Long id) {
        log.info("Deleting book with id: {}", id);
        BookResponse bookResponse = getBookById(id);
        bookRepository.delete(bookMapper.bookDtoToBook(bookResponse));
        return bookResponse;
    }

    private Book getBookByIdOrThrow(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        String.format(BOOK_NOT_FOUND_BY_ID, id)));
    }

    private Book getBookByIsbnOrThrow(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() ->
                new ResourceNotFoundException(
                        String.format(BOOK_NOT_FOUND_BY_ISBN, isbn)));
    }

    private void updateExistingBook(BookResponse existingBook, BookResponse newBook) {
        existingBook.setIsbn(newBook.getIsbn());
        existingBook.setName(newBook.getName());
        existingBook.setDescription(newBook.getDescription());
        existingBook.setGenre(newBook.getGenre());
        existingBook.setAuthor(newBook.getAuthor());
    }
}
