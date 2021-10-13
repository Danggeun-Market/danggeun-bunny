package com.example.danggeunbunny.exception;

import com.example.danggeunbunny.exception.user.UnAuthorizedAccessException;
import com.example.danggeunbunny.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_NOT_FOUND;
import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_UNAUTHORIZED;


@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpStatus> userNotFoundException() {
        return RESPONSE_NOT_FOUND;
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<HttpStatus> unAuthorizedAccessException() {
        return RESPONSE_UNAUTHORIZED;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validationNotValidException(MethodArgumentNotValidException e) {

        return new ResponseEntity< >(e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
}
