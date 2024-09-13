package com.example.BookMyShow.exception;

public class InvalidAuditoriumFeature extends ValidateAuditoriumException{
    public InvalidAuditoriumFeature() {
    }

    public InvalidAuditoriumFeature(String message) {
        super(message);
    }
}
