package com.example.BookMyShow.exception;

public class ValidateMovieControllerException extends RuntimeException{
    public ValidateMovieControllerException() {
    }

    public ValidateMovieControllerException(String message) {
        super(message);
    }
}
