package com.example.danggeunbunny.repository.user;

import com.example.danggeunbunny.model.user.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRequest, Long> {

    public boolean existsByEmail(String email);
    }

}
