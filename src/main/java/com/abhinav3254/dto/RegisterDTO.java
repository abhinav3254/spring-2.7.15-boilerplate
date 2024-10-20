package com.abhinav3254.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
}
