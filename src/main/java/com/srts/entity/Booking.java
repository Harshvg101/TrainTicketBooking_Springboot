package com.srts.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private String firstName;
    private String lastName;
    private String departure;
    private String destination;
    private LocalDateTime bookingDate;
    private String paymentType;
    private int age;
    private String gender;
    private String status; // Pending, Confirmed, Cancelled
    private String confirmationNumber;

    // Constructors
    public Booking() {}

    public Booking(String userEmail, String firstName, String lastName, String departure,
                   String destination, LocalDateTime bookingDate, String paymentType,
                   int age, String gender, String status, String confirmationNumber) {
        this.userEmail = userEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departure = departure;
        this.destination = destination;
        this.bookingDate = bookingDate;
        this.paymentType = paymentType;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.confirmationNumber = confirmationNumber;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getConfirmationNumber() { return confirmationNumber; }
    public void setConfirmationNumber(String confirmationNumber) { this.confirmationNumber = confirmationNumber; }
}
