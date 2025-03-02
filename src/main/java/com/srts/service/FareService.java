package com.srts.service;

import org.springframework.stereotype.Service;
import com.srts.entity.Passenger;
import com.srts.entity.TrainStation;
import com.srts.exception.*;

@Service
public class FareService {

    // {TODO} Method to calculate fare based on stop numbers
    public double calculateFare(TrainStation start, TrainStation end) {
        if (start == null || end == null) {
            throw new InvalidDestinationException("Invalid station details provided.");
        }

        // Example: Fare = (Difference in stops) * Rate per stop
        int stopDifference = Math.abs(end.getStopNumber() - start.getStopNumber());
        double farePerStop = 10.0;  // {TODO} Work on this its only Example fare rate

        return stopDifference * farePerStop;
    }

    // {TODO} Method to validate credit card using Luhn Algorithm not entirely same as the one we have in documents:
    public boolean validateCreditCard(String cardNumber) {
        if (cardNumber == null || !cardNumber.matches("\\d+")) {
            throw new InvalidCreditCardNumberException("Invalid credit card number.");
        }
        return luhnCheck(cardNumber);
    }

    // Luhn Algorithm for Credit Card Validation
    private boolean luhnCheck(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
