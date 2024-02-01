package com.example.bookservice.mapper;

import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.model.BookModel;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookDTO bookToBookDto(BookModel bookModel);

    BookModel bookDtoToBook(BookDTO bookDTO);
}
