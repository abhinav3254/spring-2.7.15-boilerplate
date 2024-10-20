package com.abhinav3254.controller;


import com.abhinav3254.dto.LoginRequestDTO;
import com.abhinav3254.dto.RegisterDTO;
import com.abhinav3254.dto.ResponseDTO;
import com.abhinav3254.dto.TokenResponseDTO;
import com.abhinav3254.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class Auth {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    private ResponseDTO register(@RequestBody RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }

    @PostMapping("/login")
    private TokenResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        return authService.login(loginRequest);
    }

}
