package com.example.danggeunbunny.domain.user.presentation.dto.request;

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
