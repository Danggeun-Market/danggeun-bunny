package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.model.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void registrationUser(User userRequest);

    public boolean isDuplicatedEMail(String email);


}
