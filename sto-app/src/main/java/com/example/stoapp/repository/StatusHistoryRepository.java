package com.example.stoapp.repository;

import com.example.stoapp.model.RepairRequest;
import com.example.stoapp.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {
    List<StatusHistory> findByRequestOrderByChangedAtDesc(RepairRequest request);
}
