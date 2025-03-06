package com.srts.service;

import com.srts.entity.Booking;
import com.srts.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Create a new booking.
     * @param booking Booking details.
     * @return Saved Booking object.
     */
    public Booking createBooking(Booking booking) {
        booking.setStatus("Pending"); // Default status
        booking.setConfirmationNumber(generateConfirmationNumber());
        return bookingRepository.save(booking);
    }

    /**
     * Retrieve bookings by user email.
     * @param userEmail Email of the user.
     * @return List of bookings.
     */
    public List<Booking> getBookingsByUserEmail(String userEmail) {
        return bookingRepository.findByUserEmail(userEmail);
    }

    /**
     * Get a booking by ID.
     * @param id Booking ID.
     * @return Optional Booking object.
     */
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    /**
     * Confirm a booking.
     * @param id Booking ID.
     * @return Updated Booking object.
     */
    public Booking confirmBooking(Long id) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus("Confirmed");
            return bookingRepository.save(booking);
        }
        throw new RuntimeException("Booking not found.");
    }

    /**
     * Delete a booking by ID.
     * @param id Booking ID.
     */
    public void deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Booking with ID " + id + " not found.");
        }
    }

    /**
     * Generate a random confirmation number.
     * @return Confirmation number.
     */
    private String generateConfirmationNumber() {
        return "CNF" + new Random().nextInt(999999);
    }
}
