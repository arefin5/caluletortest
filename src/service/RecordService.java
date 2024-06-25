package com.example.calculator.service;

import com.example.calculator.entity.Record;
import com.example.calculator.entity.User;
import com.example.calculator.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public List<Record> findByUser(User user) {
        return recordRepository.findByUserAndDeletedFalse(user);
    }

    public Record findById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    public Record save(Record record) {
        return recordRepository.save(record);
    }

    public void softDelete(Record record) {
        record.setDeleted(true);
        recordRepository.save(record);
    }
}
