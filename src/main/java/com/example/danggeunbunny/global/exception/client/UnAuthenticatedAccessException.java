package com.example.danggeunbunny.global.exception.client;

public class UnAuthenticatedAccessException extends RuntimeException {

    public UnAuthenticatedAccessException() {
        super();
    }

    public UnAuthenticatedAccessException(String message) {
        super(message);
    }

    public UnAuthenticatedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
