package com.example.BookMyShow.exception;

public class TheaterNotFoundException extends RuntimeException {

    public TheaterNotFoundException(String message) {
        super(message);
    }
}
