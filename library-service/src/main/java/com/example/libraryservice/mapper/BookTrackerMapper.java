package com.example.libraryservice.mapper;

import com.example.libraryservice.dto.BookTrackerResponse;
import com.example.libraryservice.model.BookTracker;
import org.mapstruct.Mapper;

@Mapper
public interface BookTrackerMapper {
    BookTrackerResponse bookTrackerToBookTrackerDto(BookTracker bookTracker);

    BookTracker bookTrackerDtoToBookTracker(BookTrackerResponse bookTrackerResponse);
}
