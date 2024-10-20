package com.abhinav3254.repository;


import com.abhinav3254.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {

    Optional<Users> findByEmail(String email);

}
