package com.abhinav3254.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class TokenResponseDTO {
    private String name;
    private String message;
    private String token;
}
