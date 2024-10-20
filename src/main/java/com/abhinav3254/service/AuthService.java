package com.abhinav3254.service;


import com.abhinav3254.dto.LoginRequestDTO;
import com.abhinav3254.dto.RegisterDTO;
import com.abhinav3254.dto.ResponseDTO;
import com.abhinav3254.dto.TokenResponseDTO;
import com.abhinav3254.exception.CustomException;
import com.abhinav3254.jwt.JwtUtils;
import com.abhinav3254.model.ROLES;
import com.abhinav3254.model.Users;
import com.abhinav3254.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public ResponseDTO register(RegisterDTO registerDTO) {
        Users users = configUser(registerDTO);
        usersRepository.save(users);
        return new ResponseDTO("User Created",200);
    }

    private Users configUser(RegisterDTO registerDTO) {
        Users users = new Users();
        users.setId(UUID.randomUUID().toString());
        users.setName(registerDTO.getName());
        users.setEmail(registerDTO.getEmail());
        users.setPassword(registerDTO.getPassword());
        users.setRole(ROLES.USER.toString());
        users.setPhone(registerDTO.getPhone());
        return users;
    }

    public TokenResponseDTO login(LoginRequestDTO loginRequest) {
        Optional<Users> usersOptional = usersRepository.findByEmail(loginRequest.getEmail());
        if (usersOptional.isEmpty()) throw new CustomException("User not found",404);
        Users users = usersOptional.get();
        String token = jwtUtils.generateToken(users.getId(),users.getEmail(),users.getRole());
        return new TokenResponseDTO(users.getName(),"Welcome Back "+users.getName()+"!",token);
    }
}
