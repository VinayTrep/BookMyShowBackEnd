package com.example.BookMyShow.exception;

public class InvalidSeatStatusException extends ValidateSeatControllerException{

    public InvalidSeatStatusException(String message) {
        super(message);
    }
}
