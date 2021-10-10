package com.example.danggeunbunny.exception;

import com.example.danggeunbunny.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_NOT_FOUND;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpStatus> userNotFoundException() {
        return RESPONSE_NOT_FOUND;
    }
}
