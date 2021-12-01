package com.example.danggeunbunny.domain.login.service;

import com.example.danggeunbunny.domain.user.domain.entity.User;

public interface LoginService {

     void login(long id);

     void logout();

     User getLoginUser();

}
