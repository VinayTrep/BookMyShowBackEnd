package com.example.BookMyShow.exception;

public class InvalidMovieFeatureException extends ValidateMovieControllerException{

    public InvalidMovieFeatureException(String message) {
        super(message);
    }
}
