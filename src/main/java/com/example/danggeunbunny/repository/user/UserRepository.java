package com.example.danggeunbunny.repository.user;

import com.example.danggeunbunny.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByEmail(String email);
    
}

