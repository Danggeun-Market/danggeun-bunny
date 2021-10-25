package com.example.danggeunbunny.fixture;

import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.model.user.User;

public class UserFixture {


    public static final User ADMIN_User = new User(
            "admin@daangnmarket.com",
            "!Admin123",
            "admin"
    );

    public static final User User1 = new User(
            "user1@daangnmarket.com",
            "!User123",
            "user1"
    );

    public static final User User2 = new User(
            "user2@daangnmarket.com",
            "!User123",
            "user2"
    );

    public static final UserDto User_REGISTRATION_REQUEST = new UserDto(
            "newuser@daangnmarket.com",
            "!Newuser123",
            "newUser"
    );

    public static final User NEW_User = new User(
            "newuser@daangnmarket.com",
            "!Newuser123",
            "newUser"
    );

}
