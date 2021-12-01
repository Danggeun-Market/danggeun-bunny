package com.example.danggeunbunny.domain.user.service;

import com.example.danggeunbunny.global.dto.Request.LocationRequest;
import com.example.danggeunbunny.domain.user.presentation.dto.request.PasswordRequestDto;
import com.example.danggeunbunny.domain.user.presentation.dto.request.ProfileRequestDto;
import com.example.danggeunbunny.domain.user.presentation.dto.UserDto;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
 public interface UserService {

     void registrationUser(User userRequest);

     boolean isDuplicatedEmail(String email);

     User findUserByEmail(String email);

     boolean isValidUser(UserDto userDtoDto, PasswordEncoder passwordEncoder);

     boolean isValidPassword(User user, PasswordRequestDto passwordRequestDto, PasswordEncoder passwordEncoder);

     void updateUserProfile(User user, ProfileRequestDto profileRequestDto);

     void updateUserPassword(User user, PasswordRequestDto passwordRequestDto, PasswordEncoder passwordEncoder);

     void setUserLocationAddress(User user, LocationRequest locationRequestDto);

}
