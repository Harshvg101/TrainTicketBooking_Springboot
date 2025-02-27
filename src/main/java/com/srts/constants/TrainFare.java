package com.srts.constants;

public interface TrainFare {
    double BASE_FARE = 10.0; // Base fare per stop
    int MAX_STATIONS = 50;   // Max train stops

    // Example static method to calculate fare
    static double calculateFare(int stops) {
        return stops * BASE_FARE;
    }
}
