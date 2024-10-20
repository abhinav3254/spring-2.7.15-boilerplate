package com.abhinav3254.exception;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(CustomException.class)
    private ExceptionWrapper customException(CustomException e) {
        return new ExceptionWrapper(e.getMessage(),e.getStatus());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ExceptionWrapper userNotFoundException(UsernameNotFoundException e) {
        return new ExceptionWrapper(e.getMessage(),404);
    }

    @ExceptionHandler(Exception.class)
    private ExceptionWrapper exception(Exception e) {
        return new ExceptionWrapper(e.getMessage(),500);
    }

}
