package com.example.danggeunbunny.aop;

import com.example.danggeunbunny.exception.board.AreaInfoNotDefinedException;
import com.example.danggeunbunny.exception.user.UserNotFoundException;
import com.example.danggeunbunny.model.user.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AreaInfoAspect {

    @Before("@annotation(com.example.danggeunbunny.annotation.AreaInfoRequired)")
    public void isValidAreaInfo(JoinPoint joinPoint) {
        User user = Arrays.stream(joinPoint.getArgs())
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .findFirst()
                .orElseThrow(UserNotFoundException::new);


        if(user.getAddress() == null || user.getLocation() == null) {
            throw new AreaInfoNotDefinedException("지역 정보를 등록해주세요.");
        }
    }



}
