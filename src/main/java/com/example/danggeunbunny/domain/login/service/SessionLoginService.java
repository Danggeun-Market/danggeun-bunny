package com.example.danggeunbunny.domain.login.service;

import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private final UserService userService;
    public static final String MEMBER_ID = "MEMBER_ID";

    @Override
    public void login(long id) {
        httpSession.setAttribute(MEMBER_ID, id);
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(MEMBER_ID);
    }

    @Override
    public User getLoginUser() {
        Long UserId = (Long) httpSession.getAttribute(MEMBER_ID);

        return userService.findUserByEmail(MEMBER_ID);
    }
}
