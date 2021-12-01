package com.example.danggeunbunny.domain.user.exception;

public class UnAuthorizedAccessException extends Throwable {

    public UnAuthorizedAccessException() {
        super();
    }

    public UnAuthorizedAccessException(String message) {
        super(message);
    }

    public UnAuthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }


}
