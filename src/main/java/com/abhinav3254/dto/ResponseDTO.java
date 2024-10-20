package com.abhinav3254.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResponseDTO {
    private String message;
    private int status;
}
