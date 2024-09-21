package com.example.BookMyShow.exception;

public class InvalidPasswordFormateException extends ValidateUserControllerException {

    public InvalidPasswordFormateException(String message) {
        super(message);
    }
}
