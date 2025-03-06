package com.srts.controller;

import com.srts.entity.Booking;
import com.srts.service.BookingService;
import com.srts.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * API to create a new booking.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestHeader("Authorization") String token,
                                           @RequestBody Booking booking) {
        String userEmail = jwtUtil.extractUsername(token.substring(7));
        booking.setUserEmail(userEmail);
        booking.setBookingDate(LocalDateTime.now());
        Booking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    /**
     * API to fetch user bookings.
     */
    @GetMapping("/myBookings")
    public ResponseEntity<?> getUserBookings(@RequestHeader("Authorization") String token) {
        String userEmail = jwtUtil.extractUsername(token.substring(7));
        List<Booking> bookings = bookingService.getBookingsByUserEmail(userEmail);

        if (bookings.isEmpty()) {
            return ResponseEntity.ok("No bookings found.");
        }
        return ResponseEntity.ok(bookings);
    }

    /**
     * API to confirm a booking.
     */
    @PutMapping("/confirm/{id}")
    public ResponseEntity<?> confirmBooking(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        Booking booking = bookingService.confirmBooking(id);
        return ResponseEntity.ok(booking);
    }

    /**
     * API to delete a booking.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully.");
    }
}
