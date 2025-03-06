package com.srts.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_inquiries")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private String title;

    @Column(length = 1000)  // Allow up to 1000 words
    private String message;

    private LocalDateTime submittedAt;
    private boolean isReplied;

    // Default Constructor
    public Contact() {}

    // Constructor
    public Contact(String userEmail, String title, String message) {
        this.userEmail = userEmail;
        this.title = title;
        this.message = message;
        this.submittedAt = LocalDateTime.now();
        this.isReplied = false;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    public boolean isReplied() { return isReplied; }
    public void setReplied(boolean replied) { isReplied = replied; }
}
