package com.example.BookMyShow.exception;

public class InvalidCapacityException extends ValidateAuditoriumException{
    public InvalidCapacityException() {
    }

    public InvalidCapacityException(String message) {
        super(message);
    }
}
