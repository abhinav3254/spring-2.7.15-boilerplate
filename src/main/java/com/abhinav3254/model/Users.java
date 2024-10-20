package com.abhinav3254.model;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    private String id;

    @NotBlank(message = "Name is required")
    @Column(name = "name",nullable = false,length = 55)
    private String name;

    @Column(name = "email",nullable = false,unique = true,length = 55)
    private String email;

    @Column(name = "password",nullable = false,length = 255)
    private String password;

    @Column(name = "phone",nullable = false,length = 10)
    private String phone;

    @Column(name = "role",nullable = false,length = 55)
    private String role;
}
