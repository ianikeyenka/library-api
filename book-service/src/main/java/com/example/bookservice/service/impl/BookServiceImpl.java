package com.example.bookservice.service.impl;

import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.model.BookModel;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public List<BookDTO> getBooks() {
        log.info("Fetching all books");
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDTO getBookById(Long id) {
        log.info("Fetching book with id: {}", id);
        return bookMapper.bookToBookDto(
                bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                String.format(BOOK_NOT_FOUND_BY_ID, id)
                        )));
    }

    @Override
    @Transactional
    public BookDTO getBookByISBN(String isbn) {
        log.info("Fetching book with ISBN: {}", isbn);
        BookModel bookModel = bookRepository.findByIsbn(isbn).orElseThrow(() ->
                new ResourceNotFoundException(String.format(BOOK_NOT_FOUND_BY_ISBN, isbn)));
        return bookMapper.bookToBookDto(bookModel);
    }

    @Override
    @Transactional
    public void addBook(BookDTO bookDTO) {
        log.info("Adding new book: {}", bookDTO);
        bookRepository.save(bookMapper.bookDtoToBook(bookDTO));
    }

    @Override
    @Transactional
    public void updateBook(Long id, BookDTO bookDTO) {
        log.info("Updating book with id: {}", id);
        BookDTO existingBook = bookMapper
                .bookToBookDto(bookRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                String.format(BOOK_NOT_FOUND_BY_ID, id))));
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setName(bookDTO.getName());
        existingBook.setDescription(bookDTO.getDescription());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setAuthor(bookDTO.getAuthor());
        bookRepository.save(bookMapper.bookDtoToBook(existingBook));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        log.info("Deleting book with id: {}", id);
        bookRepository.delete(bookMapper.bookDtoToBook(getBookById(id)));
    }
}
