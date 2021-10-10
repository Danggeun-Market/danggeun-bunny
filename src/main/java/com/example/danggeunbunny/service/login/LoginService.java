package com.example.danggeunbunny.service.login;

import com.example.danggeunbunny.model.user.User;

public interface LoginService {

    public void login(User user);

    public void logout();

    public User getLoginUser(long id);

}
