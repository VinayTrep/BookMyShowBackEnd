package com.example.BookMyShow.exception;

public class MovieNotFoundException extends RuntimeException {


    public MovieNotFoundException(String message) {
        super(message);
    }
}
