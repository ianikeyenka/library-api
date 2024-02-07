package com.example.libraryservice.repository;

import com.example.libraryservice.model.BookTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookTrackerRepository extends JpaRepository<BookTracker, Long> {
    @Query("from BookTracker as t where CURRENT_DATE > t.dateToReturn")
    List<BookTracker> findFreeBooks();
}
