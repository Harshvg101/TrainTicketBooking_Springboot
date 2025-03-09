package com.srts.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "passenger")
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
    public Passenger() {
    }

    public Passenger(Long id, String name, String destination, String paymentType, Double fare, LocalDateTime transactionTime) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.paymentType = paymentType;
        this.fare = fare;
        this.transactionTime = transactionTime;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(Long id) { this.name = name; }

    public String getDestination() { return destination; }
    public void setDestination(Long id) { this.destination = destination; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(Long id) { this.paymentType = paymentType; }

    public Double getFare() { return fare; }
    public void setFare(Long id) { this.fare = fare; }

    public LocalDateTime getTransactionTime() { return transactionTime; }
    public void setTransactionTime(LocalDateTime transactionTime) { this.transactionTime = transactionTime; }
}
