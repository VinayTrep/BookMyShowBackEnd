package com.example.BookMyShow.exception;

public class InvalidSeatTypeException extends ValidateSeatControllerException {
    public InvalidSeatTypeException() {
    }

    public InvalidSeatTypeException(String message) {
        super(message);
    }
}
