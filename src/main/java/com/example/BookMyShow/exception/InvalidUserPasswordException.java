package com.example.BookMyShow.exception;

public class InvalidUserPasswordException extends RuntimeException{
    public InvalidUserPasswordException() {
    }

    public InvalidUserPasswordException(String message) {
        super(message);
    }
}
