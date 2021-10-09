package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.model.user.UserRequest;
import com.example.danggeunbunny.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void registrationUser(UserRequest userRequest) {
        userRepository.save(UserRequest);

    }
}
