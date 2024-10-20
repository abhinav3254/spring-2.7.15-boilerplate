package com.abhinav3254.controller;


import com.abhinav3254.model.Users;
import com.abhinav3254.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public Users getProfile() {
        return userService.getProfile();
    }

}
