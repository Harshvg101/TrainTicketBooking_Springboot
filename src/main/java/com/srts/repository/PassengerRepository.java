package com.srts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srts.entity.Passenger;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByName(String name);
}
