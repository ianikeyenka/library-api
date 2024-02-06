package com.example.bookservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.bookservice.util.Constant.AUTHOR_SIZE;
import static com.example.bookservice.util.Constant.DESCRIPTION_SIZE;
import static com.example.bookservice.util.Constant.FIELD_CANNOT_BE_EMPTY;
import static com.example.bookservice.util.Constant.GENRE_SIZE;
import static com.example.bookservice.util.Constant.ISBN_FORMAT_REQUIREMENT;
import static com.example.bookservice.util.Constant.NAME_SIZE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;

    @NotBlank(message = FIELD_CANNOT_BE_EMPTY)
    @Pattern(regexp = "(^\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d$)",
            message = ISBN_FORMAT_REQUIREMENT)
    private String isbn;

    @NotBlank(message = FIELD_CANNOT_BE_EMPTY)
    @Size(min = 1, max = 100, message = NAME_SIZE)
    private String name;

    @NotBlank(message = FIELD_CANNOT_BE_EMPTY)
    @Size(min = 1, max = 50, message = GENRE_SIZE)
    private String genre;

    @NotBlank(message = FIELD_CANNOT_BE_EMPTY)
    @Size(min = 1, max = 500, message = DESCRIPTION_SIZE)
    private String description;

    @NotBlank(message = FIELD_CANNOT_BE_EMPTY)
    @Size(min = 1, max = 100, message = AUTHOR_SIZE)
    private String author;
}

