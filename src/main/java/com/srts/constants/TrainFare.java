package com.srts.constants;

public interface TrainFare {
    // {TODO} Implement the TrainFare functionaluty
    double BASE_FARE = 10.0; // Base fare per stop
    int MAX_STATIONS = 50;   // Max train stops

    // {TODO} A Place holder Example static method to calculate fare
    static double calculateFare(int stops) {
        return stops * BASE_FARE;
    }
}
