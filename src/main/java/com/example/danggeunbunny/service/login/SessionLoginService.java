package com.example.danggeunbunny.service.login;

import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{

    private final HttpSession httpSession;
    private final UserService userService;
    private static final String MEMBER_ID = "MEMBER_ID";

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
