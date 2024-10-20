package com.abhinav3254.service;


import com.abhinav3254.jwt.JWTFilter;
import com.abhinav3254.model.Users;
import com.abhinav3254.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JWTFilter jwtFilter;

    public Users getProfile() {
        return usersRepository.findById(jwtFilter.getUserId()).get();
    }
}
