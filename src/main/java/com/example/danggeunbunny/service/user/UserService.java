package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.model.user.UserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void registrationUser(UserRequest userRequest);

    public boolean isDuplicatedEMail(String email);


}
