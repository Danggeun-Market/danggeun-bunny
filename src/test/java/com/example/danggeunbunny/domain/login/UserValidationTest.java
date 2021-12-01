package com.example.danggeunbunny.domain.login;

import com.example.danggeunbunny.domain.user.presentation.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    @DisplayName("사용자가 정상적인 이메일과 패스워드를 입력한 경우 정상적으로 유효성 검사 통과")
    void isCorrectValidation() {
        UserDto userDto = UserDto.builder()
                .email("lyutvs@gmail.com")
                .password("pillar003)!")
                .nickname("엄청난노옴")
                .build();

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("사용자의 이메일 형식이 올바르지 않을 경우 유효성 검사에 실패")
    void isNotValidEmail() {
        UserDto userDto = UserDto.builder()
                .email("lyutvs@gmail.com")
                .password("pillar003)!")
                .nickname("엄청난노옴")
                .build();

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertEquals("유효하지 않은 이메일 형식입니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("사용자의 패스워드 형식이 올바르지 않을 경유 유효성 검사 실패")
    void isNotValidPassword() {
        UserDto userDto = UserDto.builder()
                .email("lyutvs@gmail.com")
                .password("pillar003)!")
                .nickname("엄청난노옴")
                .build();

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertEquals("최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.", violations.iterator().next().getMessage());
    }
}
