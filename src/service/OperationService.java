package com.example.calculator.service;

import com.example.calculator.entity.Operation;
import com.example.calculator.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public Operation findByType(String type) {
        return operationRepository.findByType(type);
    }

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }
}
