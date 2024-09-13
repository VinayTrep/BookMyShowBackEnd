package com.example.BookMyShow.exception;

public class CityNameEmptyException extends RuntimeException {
    public CityNameEmptyException() {
    }

    public CityNameEmptyException(String message) {
        super(message);
    }
}
