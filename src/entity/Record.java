package com.example.calculator.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double amount;
    private Double userBalance;
    private String operationResponse;
    private Date date;

    private Boolean deleted = false; // Soft delete flag
}
