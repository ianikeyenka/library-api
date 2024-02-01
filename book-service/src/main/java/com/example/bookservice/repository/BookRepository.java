package com.example.bookservice.repository;

import com.example.bookservice.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    BookModel findByIsbn(String isbn);
}
