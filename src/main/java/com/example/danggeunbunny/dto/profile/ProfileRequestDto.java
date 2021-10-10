package com.example.danggeunbunny.dto.profile;

import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@BooleanFlag
@Getter
@RequiredArgsConstructor
public class ProfileRequestDto {

    private final String email;

    private final String nickname;

}
