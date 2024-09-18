package com.example.BookMyShow.exception;

public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException() {
    }

    public ActorNotFoundException(String message) {
        super(message);
    }
}
