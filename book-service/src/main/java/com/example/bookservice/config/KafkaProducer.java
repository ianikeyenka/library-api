package com.example.bookservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Long> kafkaTemplate;

    public void sendBookId(String topic, Long bookId) {
        kafkaTemplate.send(topic, bookId);
    }
}
