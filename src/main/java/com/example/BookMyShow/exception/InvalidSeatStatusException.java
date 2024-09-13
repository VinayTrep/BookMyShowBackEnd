package com.example.BookMyShow.exception;

public class InvalidSeatStatusException extends ValidateSeatControllerException{
    public InvalidSeatStatusException() {
    }

    public InvalidSeatStatusException(String message) {
        super(message);
    }
}
