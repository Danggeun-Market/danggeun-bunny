package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.exception.user.UserNotFoundException;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GeneralUserServiceImpl implements UserService{

    private final UserRepository memberRepository;

    @Override
    @Transactional
    public void registrationUser(User user) {
        memberRepository.save(user);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return memberRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean isValidUser(UserDto userDto, PasswordEncoder passwordEncoder) {

        User user = findUserByEmail(userDto.getEmail());

        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

            return true;
        }

        return false;
    }

}
