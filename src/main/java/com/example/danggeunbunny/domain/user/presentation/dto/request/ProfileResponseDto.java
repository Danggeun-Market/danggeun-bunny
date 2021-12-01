package com.example.danggeunbunny.domain.user.presentation.dto.request;

import com.example.danggeunbunny.domain.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class ProfileResponseDto {

    private final Long id;
    private final String email;
    private final String nickname;

    public static ProfileResponseDto of(User user) {
        return ProfileResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

}
