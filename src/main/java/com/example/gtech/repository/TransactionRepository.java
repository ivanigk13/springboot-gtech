package com.example.gtech.repository;

import com.example.gtech.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
