package com.example.bookservice.service.impl;

import com.example.bookservice.config.KafkaProducer;
import com.example.bookservice.dto.BookListResponse;
import com.example.bookservice.dto.BookRequest;
import com.example.bookservice.dto.BookResponse;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.example.bookservice.util.Constant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final KafkaProducer kafkaProducer;

    @Override
    public BookListResponse getBooks() {
        log.info("Fetching all books");
        return new BookListResponse(
                bookRepository.findAll().stream()
                        .map(bookMapper::bookToBookDto)
                        .collect(Collectors.toList()));
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

    private Book getBookByIsbnOrThrow(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() ->
                new ResourceNotFoundException(
                        String.format(BOOK_NOT_FOUND_BY_ISBN, isbn)));
    }

    @Override
    @Transactional
    public BookResponse saveBook(BookRequest bookRequest) {
        log.info("Adding new book: {}", bookRequest);
        Book book = bookMapper.bookDtoToBook(bookRequest);
        existingIsbn(book.getIsbn());
        bookRepository.save(book);
        kafkaProducer.sendBookId("bookTrackerTopic", book.getId());
        return bookMapper.bookToBookDto(book);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        log.info("Updating book with id: {}", id);
        Book existingBook = getBookByIdOrThrow(id);
        existingBook = updateExistingBook(existingBook, bookRequest);
        existingIsbn(existingBook.getIsbn());
        bookRepository.save(existingBook);
        return bookMapper.bookToBookDto(existingBook);
    }

    private void existingIsbn(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new ResourceNotFoundException(
                    String.format(BOOK_WITH_ISBN_ALREADY_EXIST, isbn)
            );
        }
    }

    private Book updateExistingBook(Book existingBook, BookRequest newBook) {
        return Book.builder()
                .id(existingBook.getId())
                .isbn(newBook.getIsbn())
                .name(newBook.getName())
                .description(newBook.getDescription())
                .genre(newBook.getGenre())
                .author(newBook.getAuthor())
                .build();
    }

    @Override
    public BookResponse deleteBook(Long id) {
        log.info("Deleting book with id: {}", id);
        Book book = getBookByIdOrThrow(id);
        bookRepository.delete(book);
        return bookMapper.bookToBookDto(book);
    }

    private Book getBookByIdOrThrow(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        String.format(BOOK_NOT_FOUND_BY_ID, id)));
    }
}
