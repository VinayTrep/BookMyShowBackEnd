package com.example.BookMyShow.exception;

public class InvalidEmailFormateException extends ValidateUserControllerException {
    public InvalidEmailFormateException() {
    }

    public InvalidEmailFormateException(String message) {
        super(message);
    }
}
