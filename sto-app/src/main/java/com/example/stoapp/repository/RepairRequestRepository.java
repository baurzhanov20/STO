package com.example.stoapp.repository;

import com.example.stoapp.model.RepairRequest;
import com.example.stoapp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepairRequestRepository extends JpaRepository<RepairRequest, Long> {
        List<RepairRequest> findByClientName(String clientName);
        List<RepairRequest> findByStatus(Status status);
        List<RepairRequest> findByClientPhone(String phone);
}

