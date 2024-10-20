package com.abhinav3254.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionWrapper> handleCustomException(CustomException e) {
        ExceptionWrapper response = new ExceptionWrapper(e.getMessage(), e.getStatus());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionWrapper> handleUserNotFoundException(UsernameNotFoundException e) {
        ExceptionWrapper response = new ExceptionWrapper(e.getMessage(), 404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionWrapper> handleGenericException(Exception e) {
        ExceptionWrapper response = new ExceptionWrapper(e.getMessage(), 500);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
