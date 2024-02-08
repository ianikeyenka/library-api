package com.example.libraryservice.config;

import com.example.libraryservice.service.BookTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListenerConfig {

    private final BookTrackerService bookTrackerService;

    @KafkaListener(topics = "bookTrackerTopic")
    public void handleBook(Long bookId) {
        bookTrackerService.saveToBookTracker(bookId);
    }
}
