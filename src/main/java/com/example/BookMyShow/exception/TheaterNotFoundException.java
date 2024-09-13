package com.example.BookMyShow.exception;

public class TheaterNotFoundException extends RuntimeException {
    public TheaterNotFoundException() {
    }

    public TheaterNotFoundException(String message) {
        super(message);
    }
}
