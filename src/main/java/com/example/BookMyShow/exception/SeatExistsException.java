package com.example.BookMyShow.exception;

public class SeatExistsException extends RuntimeException{
    public SeatExistsException() {
    }

    public SeatExistsException(String message) {
        super(message);
    }
}
