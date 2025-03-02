package com.srts.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srts.entity.Passenger;
import com.srts.repository.PassengerRepository;
import com.srts.exception.NoRecordFoundException;

@Service
public class            PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerByName(String name) {
        return passengerRepository.findByName(name)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoRecordFoundException("No passenger found with name: " + name));
    }
}
