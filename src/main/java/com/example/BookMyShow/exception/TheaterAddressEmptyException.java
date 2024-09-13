package com.example.BookMyShow.exception;

public class TheaterAddressEmptyException extends ValidateTheaterException{
    public TheaterAddressEmptyException() {
    }

    public TheaterAddressEmptyException(String message) {
        super(message);
    }
}
