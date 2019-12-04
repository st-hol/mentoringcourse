package com.epam.exercises.circularbuffer.exception;

public class BufferIllegalStateException extends RuntimeException {
    public BufferIllegalStateException() {
    }

    public BufferIllegalStateException(String message) {
        super(message);
    }
}
