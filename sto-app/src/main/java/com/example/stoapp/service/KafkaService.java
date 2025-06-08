package com.example.stoapp.service;

import com.example.stoapp.model.Status;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private static final String TOPIC = "status-changes";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStatusChange(Long requestId, Status oldStatus, Status newStatus) {
        String message = String.format(
                "{\"requestId\":%d,\"oldStatus\":\"%s\",\"newStatus\":\"%s\"}",
                requestId, oldStatus, newStatus
        );
        kafkaTemplate.send(TOPIC, message);
    }
}
