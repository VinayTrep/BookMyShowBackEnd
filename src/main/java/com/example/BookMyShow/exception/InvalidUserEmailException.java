package com.example.BookMyShow.exception;

public class InvalidUserEmailException extends RuntimeException{

    public InvalidUserEmailException(String message) {
        super(message);
    }
}
