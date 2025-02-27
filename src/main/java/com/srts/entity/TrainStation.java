package com.srts.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class TrainStation {
    @Id
    private Long id;
    private String name;
    private Integer stopNumber;

    // Constructor
    public TrainStation() {}

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStopNumber() {  // ✅ Add this getter
        return stopNumber;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStopNumber(Integer stopNumber) {  // ✅ Add this setter
        this.stopNumber = stopNumber;
    }
}