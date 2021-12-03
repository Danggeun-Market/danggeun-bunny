package com.example.danggeunbunny.global.aop;

import com.example.danggeunbunny.domain.feed.exception.AreaInfoNotDefinedException;
import com.example.danggeunbunny.domain.user.exception.UserNotFoundException;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AreaInfoAspect {

    @Before("@annotation(com.example.danggeunbunny.global.annotation.AreaInfoRequired)")
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
