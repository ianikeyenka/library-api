package com.example.libraryservice.service.impl;

import com.example.libraryservice.dto.BookTrackerResponse;
import com.example.libraryservice.mapper.BookTrackerMapper;
import com.example.libraryservice.model.BookTracker;
import com.example.libraryservice.repository.BookTrackerRepository;
import com.example.libraryservice.service.BookTrackerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookTrackerServiceImpl implements BookTrackerService {

    private final BookTrackerRepository bookTrackerRepository;
    private final BookTrackerMapper bookTrackerMapper;

    @Override
    public List<BookTrackerResponse> getBookTrackers() {
        log.info("Getting all book trackers");
        return bookTrackerRepository.findAll().stream()
                .map(bookTrackerMapper::bookTrackerToBookTrackerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookTrackerResponse> getFreeBooks() {
        log.info("Getting free books");
        return bookTrackerRepository.findFreeBooks().stream()
                .map(bookTrackerMapper::bookTrackerToBookTrackerDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookTrackerResponse saveToBookTracker(Long bookId) {
        log.info("Saving book to book tracker. Book ID: {}", bookId);
        BookTracker bookTracker = BookTracker.builder()
                .bookId(bookId)
                .dateBorrowed(LocalDate.now())
                .dateToReturn(LocalDate.now().plusWeeks(2))
                .build();
        bookTrackerRepository.save(bookTracker);
        log.info("Book saved to book tracker. Book ID: {}", bookId);
        return bookTrackerMapper.bookTrackerToBookTrackerDto(bookTracker);
    }
}
