package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void registrationUser(User userRequest) {
        userRepository.save(userRequest);

    }

    @Override
    public boolean isDuplicatedEMail(String email) {
        return userRepository.existsByEmail(email);
    }
}
