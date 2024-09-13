package com.example.BookMyShow.exception;

public class TheaterNameEmptyException extends ValidateTheaterException{
    public TheaterNameEmptyException() {
    }

    public TheaterNameEmptyException(String message) {
        super(message);
    }
}
