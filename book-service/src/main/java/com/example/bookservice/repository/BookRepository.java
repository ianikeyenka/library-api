package com.example.bookservice.repository;

import com.example.bookservice.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    Optional<BookModel> findByIsbn(String isbn);
}
