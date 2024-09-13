package com.example.BookMyShow.exception;

public class TheaterImageEmptyException extends ValidateTheaterException{
    public TheaterImageEmptyException() {
    }

    public TheaterImageEmptyException(String message) {
        super(message);
    }
}
