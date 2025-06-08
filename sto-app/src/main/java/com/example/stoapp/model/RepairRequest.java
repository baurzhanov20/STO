package com.example.stoapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String clientPhone;
    private String carDetails;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    private LocalDateTime createdAt = LocalDateTime.now();
}