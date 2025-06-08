package com.example.stoapp.service;

import com.example.stoapp.model.*;
import com.example.stoapp.repository.RepairRequestRepository;
import com.example.stoapp.repository.StatusHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairRequestService {
    private final RepairRequestRepository requestRepo;
    private final StatusHistoryRepository historyRepo;
    private final NotificationService notificationService;
    private final KafkaService kafkaService;

    public RepairRequestService(
            RepairRequestRepository requestRepo,
            StatusHistoryRepository historyRepo,
            NotificationService notificationService,
            KafkaService kafkaService
    ) {
        this.requestRepo = requestRepo;
        this.historyRepo = historyRepo;
        this.notificationService = notificationService;
        this.kafkaService = kafkaService;
    }

    public RepairRequest createRequest(RepairRequest request) {
        request.setStatus(Status.NEW);
        return requestRepo.save(request);
    }

    @Transactional
    public void updateStatus(Long requestId, Status newStatus, String changedBy, String reason) {
        RepairRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        StatusHistory history = new StatusHistory();
        history.setRequest(request);
        history.setOldStatus(request.getStatus());
        history.setNewStatus(newStatus);
        history.setChangedBy(changedBy);
        history.setReason(reason);
        historyRepo.save(history);

        request.setStatus(newStatus);
        requestRepo.save(request);

        kafkaService.sendStatusChange(requestId, request.getStatus(), newStatus);

        if (newStatus == Status.COMPLETED) {
            notificationService.notifyClient(
                    request.getClientPhone(),
                    "Ваш автомобиль готов! Заявка №" + requestId
            );
        }
    }

    public List<RepairRequest> findByClientName(String name) {
        return requestRepo.findByClientName(name);
    }

    public List<RepairRequest> findByStatus(Status status) {
        return requestRepo.findByStatus(status);
    }

    public List<StatusHistoryDto> getStatusHistory(Long id) {
        RepairRequest request = requestRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Заявка с ID " + id + " не найдена"));
        List<StatusHistory> statusHistories = historyRepo.findByRequestOrderByChangedAtDesc(request);

        return statusHistories.stream()
                .map(history -> new StatusHistoryDto(
                        history.getOldStatus(),
                        history.getNewStatus(),
                        history.getChangedAt(),
                        history.getChangedBy(),
                        history.getReason()
                ))
                .collect(Collectors.toList());
    }
}
