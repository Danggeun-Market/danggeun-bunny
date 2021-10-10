package com.example.danggeunbunny.service.login;

import com.example.danggeunbunny.model.user.User;

public interface LoginService {

    public void login(long id);

    public void logout();

    public User getLoginUser();

}
