package com.example.danggeunbunny.annotation.login;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {

}
