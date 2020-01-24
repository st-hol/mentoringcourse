package com.epam.exercises.restaurant.exception;

public class UnknownHappinessIncrementException extends RuntimeException {
    public UnknownHappinessIncrementException() {
    }

    public UnknownHappinessIncrementException(String message) {
        super(message);
    }
}
