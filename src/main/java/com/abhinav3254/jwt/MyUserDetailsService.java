package com.abhinav3254.jwt;


import com.abhinav3254.model.Users;
import com.abhinav3254.repository.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;


@Data
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    Users users = null;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()) {
            users = usersOptional.get();
            return new User(users.getId(),users.getPassword(),new ArrayList<>());
        } else throw new UsernameNotFoundException("User not found");
    }
}
