package com.example.global.interceptor.login;

import com.example.global.annotation.login.LoginRequired;
import com.example.global.exception.client.UnAuthenticatedAccessException;
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


        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Long memberId = (Long) request.getSession().getAttribute(MEMBER_ID);

            if (handlerMethod.hasMethodAnnotation(LoginRequired.class) && memberId == null) {
                throw new UnAuthenticatedAccessException();
            }
        }

        return true;
    }
}