package com.example.stoapp.controller;

import com.example.stoapp.model.*;
import com.example.stoapp.service.RepairRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RepairRequestController {

    private final RepairRequestService repairRequestService;

    public RepairRequestController(RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @PostMapping
    public ResponseEntity<RepairRequest> createRequest(@RequestBody RepairRequest request) {
        RepairRequest createdRequest = repairRequestService.createRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @GetMapping("/client/{clientName}")
    public ResponseEntity<List<RepairRequest>> getRequestsByClient(
            @PathVariable String clientName) {
        return ResponseEntity.ok(repairRequestService.findByClientName(clientName));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<RepairRequest>> getRequestsByStatus(
            @PathVariable Status status) {
        return ResponseEntity.ok(repairRequestService.findByStatus(status));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestParam Status status,
            @RequestParam(required = false, defaultValue = "system") String changedBy,
            @RequestParam(required = false) String reason) {

        repairRequestService.updateStatus(id, status, changedBy,
                reason != null ? reason : "Причина не указана");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<StatusHistoryDto>> getStatusHistory(
            @PathVariable Long id) {
        return ResponseEntity.ok(repairRequestService.getStatusHistory(id));
    }
}
