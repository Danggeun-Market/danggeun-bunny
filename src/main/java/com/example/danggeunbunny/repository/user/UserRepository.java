package com.example.danggeunbunny.repository.user;

import com.example.danggeunbunny.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByEmail(String email);

    public Optional<User> findUserByEmail(String email);

}

