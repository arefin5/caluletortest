package com.example.calculator.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Double cost;
}
