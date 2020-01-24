package com.epam.exercises.restaurant.exception;

public class UnknownCondimentException extends RuntimeException {
    public UnknownCondimentException() {
    }

    public UnknownCondimentException(String message) {
        super(message);
    }
}
