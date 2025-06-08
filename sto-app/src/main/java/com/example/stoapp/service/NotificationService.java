package com.example.stoapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void notifyClient(String phone, String message) {
        log.info("[MOCK SMS] To: {} | Message: {}", phone, message);
    }
}
