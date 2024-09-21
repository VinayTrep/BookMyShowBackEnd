package com.example.BookMyShow.exception;

public class InvalidUserPasswordException extends RuntimeException{

    public InvalidUserPasswordException(String message) {
        super(message);
    }
}
