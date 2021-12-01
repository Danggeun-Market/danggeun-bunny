package com.example.danggeunbunny.domain.user.service;

import com.example.danggeunbunny.global.dto.Request.LocationRequest;
import com.example.danggeunbunny.domain.user.presentation.dto.request.PasswordRequestDto;
import com.example.danggeunbunny.domain.user.presentation.dto.request.ProfileRequestDto;
import com.example.danggeunbunny.domain.user.presentation.dto.UserDto;
import com.example.danggeunbunny.domain.user.exception.UserNotFoundException;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void registrationUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean isValidUser(UserDto userDto, PasswordEncoder passwordEncoder) {

        User user = findUserByEmail(userDto.getEmail());

        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

            return true;
        }

        return false;
    }

    @Override
    public boolean isValidPassword(User user, PasswordRequestDto passwordRequestDto, PasswordEncoder passwordEncoder) {

        if(passwordEncoder.matches(passwordRequestDto.getOldPassword(), user.getPassword())) {
            return true;
        }

        return false;    }

    @Override
    @Transactional
    public void updateUserProfile(User user, ProfileRequestDto profileRequestDto) {

        user.updateProfile(profileRequestDto.getNickname());

    }

    @Override
    public void updateUserPassword(User user, PasswordRequestDto passwordRequestDto, PasswordEncoder passwordEncoder) {

        user.updatePassword(passwordEncoder.encode(passwordRequestDto.getNewPassword()));
    }

    @Override
    @Transactional
    public void setUserLocationAddress(User user, LocationRequest locationRequestDto) {
        user.setUserLocationAddress(locationRequestDto);

    }


}
