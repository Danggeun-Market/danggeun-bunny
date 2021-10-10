package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.dto.profile.ProfileRequestDto;
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
    @Transactional
    public void updateUserProfile(User user, ProfileRequestDto profileRequestDto) {

        user.update(profileRequestDto.getNickname());

    }


}
