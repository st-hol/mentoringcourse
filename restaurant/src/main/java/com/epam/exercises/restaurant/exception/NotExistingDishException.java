package com.epam.exercises.restaurant.exception;

public class NotExistingDishException extends RuntimeException {
    public NotExistingDishException() {
    }

    public NotExistingDishException(String message) {
        super(message);
    }
}
