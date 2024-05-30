package com.saxobank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String apiKey;
    private String secretKey;
    private double dailyRisk;
    private double maxRisk;
    private double currentDayLoss;
    private double currentMaxLoss;
}