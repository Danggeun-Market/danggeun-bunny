package com.example.danggeunbunny.fixture;

import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.model.user.User;

public class UserFixture {


    public static final User ADMIN_USER = new User(
            "admin@daangnmarket.com",
            "!Admin123",
            "admin"
    );

    public static final User USER1 = new User(
            "user1@daangnmarket.com",
            "!User123",
            "user1"
    );

    public static final User USER2 = new User(
            "user2@daangnmarket.com",
            "!User123",
            "user2"
    );

    public static final UserDto USER_REGISTRATION_REQUEST = new UserDto(
            "newuser@daangnmarket.com",
            "!Newuser123",
            "newUser"
    );

    public static final User NEW_USER = new User(
            "newuser@daangnmarket.com",
            "!Newuser123",
            "newUser"
    );

}
