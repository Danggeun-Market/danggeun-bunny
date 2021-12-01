package com.example.danggeunbunny.domain.user.domain.repository;

import com.example.danggeunbunny.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findUserById(long id);

    Optional<User> findUserByEmail(String email);

}

