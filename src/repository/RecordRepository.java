package com.example.calculator.repository;

import com.example.calculator.entity.Record;
import com.example.calculator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByUserAndDeletedFalse(User user);
}
