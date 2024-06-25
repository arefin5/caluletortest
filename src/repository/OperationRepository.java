package com.example.calculator.repository;

import com.example.calculator.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Operation findByType(String type);
}
