package com.abhinav3254.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor @NoArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private int status;
}
