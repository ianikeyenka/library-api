package com.example.libraryservice.repository;

import com.example.libraryservice.model.BookTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookTrackerRepository extends JpaRepository<BookTracker, Long> {
    List<BookTracker> findByDateToReturnBeforeAndDateBorrowedIsNotNull(LocalDate currentDate);
}
