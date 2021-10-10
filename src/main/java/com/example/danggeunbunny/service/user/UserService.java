package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.dto.profile.ProfileRequestDto;
import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.model.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void registrationUser(User userRequest);

    public boolean isDuplicatedEmail(String email);

    public User findUserByEmail(String email);

    public User findUserById(long id);

    public boolean isValidUser(UserDto memberDto, PasswordEncoder passwordEncoder);

    public void updateUserProfile(User user, ProfileRequestDto profileRequest);


}
