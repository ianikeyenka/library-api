package com.example.libraryservice.service.impl;

import com.example.libraryservice.dto.BookTrackerListResponse;
import com.example.libraryservice.dto.BookTrackerRequest;
import com.example.libraryservice.dto.BookTrackerResponse;
import com.example.libraryservice.exception.ResourceNotFoundException;
import com.example.libraryservice.mapper.BookTrackerMapper;
import com.example.libraryservice.model.BookTracker;
import com.example.libraryservice.repository.BookTrackerRepository;
import com.example.libraryservice.service.BookTrackerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static com.example.libraryservice.util.Constant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookTrackerServiceImpl implements BookTrackerService {

    private final BookTrackerRepository bookTrackerRepository;
    private final BookTrackerMapper bookTrackerMapper;

    @Override
    public BookTrackerListResponse getBookTrackers() {
        log.info("Getting all book trackers");
        return new BookTrackerListResponse(
                bookTrackerRepository.findAll().stream()
                        .map(bookTrackerMapper::bookTrackerToBookTrackerDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public BookTrackerListResponse getFreeBooks() {
        log.info("Getting free books");
        return new BookTrackerListResponse(bookTrackerRepository
                .findByDateToReturnBeforeAndDateBorrowedIsNotNull(LocalDate.now())
                .stream()
                .map(bookTrackerMapper::bookTrackerToBookTrackerDto)
                .collect(Collectors.toList()));
    }

    @Override
    public BookTrackerResponse updateBook(Long id, BookTrackerRequest bookTrackerRequest) {
        log.info("Updated book with ID {}", id);
        BookTracker existingBook = getOrThrow(id);
        updateExistingBook(existingBook, bookTrackerRequest);
        bookTrackerRepository.save(existingBook);
        return bookTrackerMapper.bookTrackerToBookTrackerDto(existingBook);
    }

    private void updateExistingBook(BookTracker existingBook, BookTrackerRequest newBook) {
        existingBook.setBookId(newBook.getBookId());
        existingBook.setDateBorrowed(newBook.getDateBorrowed());
        existingBook.setDateToReturn(newBook.getDateToReturn());
    }

    @Override
    public void saveToBookTracker(Long bookId) {
        log.info("Saving book to book tracker. Book ID: {}", bookId);
        BookTracker bookTracker = BookTracker.builder()
                .bookId(bookId)
                .dateBorrowed(LocalDate.now())
                .dateToReturn(LocalDate.now().plusWeeks(2))
                .build();
        bookTrackerRepository.save(bookTracker);
        log.info("Book saved to book tracker. Book ID: {}", bookId);
    }

    private BookTracker getOrThrow(Long id) {
        return bookTrackerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        String.format(BOOK_TRACKER_NOT_FOUND_BY_ID, id)));
    }
}
