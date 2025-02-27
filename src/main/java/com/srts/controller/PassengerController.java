package com.srts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.srts.entity.Passenger;
import com.srts.service.PassengerService;
import com.srts.service.FareService;
import java.util.List;
@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @Autowired
    private FareService fareService;

    @PostMapping("/add")
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        // Fare calculation and other logic
        return passengerService.savePassenger(passenger);
    }

    @GetMapping("/all")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/search")
    public Passenger getPassengerByName(@RequestParam String name) {
        return passengerService.getPassengerByName(name);
    }
}