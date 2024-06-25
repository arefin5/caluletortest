package com.example.calculator.controller;

import com.example.calculator.entity.Operation;
import com.example.calculator.entity.Record;
import com.example.calculator.entity.User;
import com.example.calculator.service.OperationService;
import com.example.calculator.service.RecordService;
import com.example.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/operations")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @PostMapping("/{type}")
    public Map<String, Object> performOperation(@PathVariable String type, @RequestBody Map<String, Double> operands, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Operation operation = operationService.findByType(type);

        if (user.getBalance() < operation.getCost()) {
            throw new RuntimeException("Insufficient balance");
        }

        double result = 0.0;
        switch (type) {
            case "addition":
                result = operands.get("operand1") + operands.get("operand2");
                break;
            case "subtraction":
                result = operands.get("operand1") - operands.get("operand2");
                break;
            case "multiplication":
                result = operands.get("operand1") * operands.get("operand2");
                break;
            case "division":
                if (operands.get("operand2") == 0) {
                    throw new RuntimeException("Division by zero");
                }
                result = operands.get("operand1") / operands.get("operand2");
                break;
            case "square_root":
                result = Math.sqrt(operands.get("operand"));
                break;
            case "random_string":
                result = generateRandomString();
                break;
            default:
                throw new IllegalArgumentException("Invalid operation type");
        }

        user.setBalance(user.getBalance() - operation.getCost());
        userService.save(user);

        Record record = new Record();
        record.setOperation(operation);
        record.setUser(user);
        record.setAmount(result);
        record.setUserBalance(user.getBalance());
        record.setOperationResponse(String.valueOf(result));
        record.setDate(new Date());
        recordService.save(record);

        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        response.put("userBalance", user.getBalance());
        return response;
    }

    private double generateRandomString() {
        // Use a third-party API to generate a random string.
        // This is a placeholder implementation.
        return Math.random();
    }
}
