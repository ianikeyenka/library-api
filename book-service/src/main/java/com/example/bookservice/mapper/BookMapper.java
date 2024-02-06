package com.example.bookservice.mapper;

import com.example.bookservice.dto.BookResponse;
import com.example.bookservice.model.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookResponse bookToBookDto(Book bookModel);

    Book bookDtoToBook(BookResponse bookResponse);
}
