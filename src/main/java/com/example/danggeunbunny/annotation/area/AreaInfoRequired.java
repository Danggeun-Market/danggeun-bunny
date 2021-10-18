package com.example.danggeunbunny.annotation.area;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AreaInfoRequired {
}
