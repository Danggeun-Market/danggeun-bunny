package com.example.danggeunbunny.annotation.login;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
