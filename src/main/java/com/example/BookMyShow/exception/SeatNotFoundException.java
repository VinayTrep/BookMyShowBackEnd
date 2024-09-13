package com.example.BookMyShow.exception;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException() {
    }

    public SeatNotFoundException(String message) {
        super(message);
    }
}
