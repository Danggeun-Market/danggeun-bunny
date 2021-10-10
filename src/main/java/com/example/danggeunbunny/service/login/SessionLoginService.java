package com.example.danggeunbunny.service.login;

import com.example.danggeunbunny.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{

    private final HttpSession httpSession;
    private static final String MEMBER_ID = "MEMBER_ID";

    @Override
    public void login(User user) {
        httpSession.setAttribute(MEMBER_ID, user);
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(MEMBER_ID);
    }

    @Override
    public User getLoginUser(long id) {
        return (User) httpSession.getAttribute(MEMBER_ID);
    }
}
