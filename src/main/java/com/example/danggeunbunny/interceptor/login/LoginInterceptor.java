package com.example.danggeunbunny.interceptor.login;

import com.example.danggeunbunny.annotation.login.LoginRequired;
import com.example.danggeunbunny.exception.user.UnAuthorizedAccessException;
import com.example.danggeunbunny.model.user.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String MEMBER_ID = "MEMBER_ID";

    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Long userId = (Long) request.getSession().getAttribute(MEMBER_ID);
        if (handlerMethod.hasMethodAnnotation(LoginRequired.class) && userId == null) {

            throw new UnAuthorizedAccessException();
        }
        return true;

    }
}