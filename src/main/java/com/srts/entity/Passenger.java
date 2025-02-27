package com.srts.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String destination;
    private String paymentType;
    private Double fare;
    private LocalDateTime transactionTime;

    // {TODO} Implement Getters and Setters
}
