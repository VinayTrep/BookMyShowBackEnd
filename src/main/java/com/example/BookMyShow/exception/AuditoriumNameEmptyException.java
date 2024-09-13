package com.example.BookMyShow.exception;

public class AuditoriumNameEmptyException extends ValidateAuditoriumException{
    public AuditoriumNameEmptyException() {
    }

    public AuditoriumNameEmptyException(String message) {
        super(message);
    }
}
