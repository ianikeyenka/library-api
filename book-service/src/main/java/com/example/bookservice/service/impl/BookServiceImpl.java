package com.example.bookservice.service.impl;

import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDTO getBookById(Long id) {
        return bookMapper.bookToBookDto(
                bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("")));
    }

    @Override
    @Transactional
    public BookDTO getBookByISBN(String isbn) {
        return bookMapper.bookToBookDto(
                bookRepository.findByIsbn(isbn));
    }

    @Override
    @Transactional
    public void addBook(BookDTO bookDTO) {
        bookRepository.save(bookMapper.bookDtoToBook(bookDTO));
    }

    @Override
    @Transactional
    public void updateBook(Long id, BookDTO bookDTO) {
        BookDTO existingBook = bookMapper
                .bookToBookDto(bookRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("")));
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
        bookRepository.delete(bookMapper.bookDtoToBook(getBookById(id)));
    }
}