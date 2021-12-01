package com.example.global.exception;

import com.example.danggeunbunny.domain.Post.exception.AreaInfoNotDefinedException;
import com.example.danggeunbunny.domain.Post.exception.CategoryNotFoundException;
import com.example.danggeunbunny.domain.Post.exception.PostNotFoundException;
import com.example.danggeunbunny.domain.user.exception.UnAuthorizedAccessException;
import com.example.danggeunbunny.domain.user.exception.UserNotFoundException;
import com.example.global.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.example.global.error.ErrorCode.*;


@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorCode userNotFoundException() {

        return RESPONSE_NOT_FOUND;
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ErrorCode unAuthorizedAccessException() {

        return RESPONSE_FORBIDDEN;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validationNotValidException(MethodArgumentNotValidException e) {

        return new ResponseEntity<>(Objects.requireNonNull(e.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AreaInfoNotDefinedException.class)
    public ResponseEntity<String> areaInfoNotDefinedException(AreaInfoNotDefinedException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ErrorCode postNotFoundException() {

        return RESPONSE_NOT_FOUND;
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ErrorCode unAuthenticatedAccessException() {

        return RESPONSE_UNAUTHORIZED;
    }

}
