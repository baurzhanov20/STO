package com.example.stoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusHistoryDto {
    private Status oldStatus;
    private Status newStatus;
    private LocalDateTime changedAt;
    private String changedBy;
    private String reason;
}
