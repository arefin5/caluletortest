package com.example.calculator.controller;

import com.example.calculator.entity.Record;
import com.example.calculator.entity.User;
import com.example.calculator.service.RecordService;
import com.example.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Record> getUserRecords(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return recordService.findByUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        Record record = recordService.findById(id);
        if (record != null) {
            recordService.softDelete(record);
        }
    }
}
