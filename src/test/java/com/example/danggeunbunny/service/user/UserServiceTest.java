package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.exception.user.UserNotFoundException;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private GeneralUserServiceImpl generalUserService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserDto userDto;

    private User user;

    @BeforeEach
    void setUp() {
        when(passwordEncoder.encode(any())).thenReturn("pillar0030!");
        userDto = UserDto.builder()
                .email("lyutvs@gmail.com")
                .password("pillar0030!")
                .nickname("엄청난노옴")
                .build();

        user = UserDto.toEntity(userDto, passwordEncoder);
    }

    @Test
    @DisplayName("중복된 이메일이 존재하지 않는 경우")
    void isNotDuplicatedEmailExist() {

        // given
        when(userRepository.existsByEmail(any())).thenReturn(true);

        // then
        assertTrue(generalUserService.isDuplicatedEmail(user.getEmail()));

    }

    @Test
    @DisplayName("해당 이메일로 가입된 회원이 존재하는 경우")
    void isExistUserFindByEmail() {

        // given
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.of(user));

        // when
        User findByEmailUser = generalUserService.findUserByEmail(user.getEmail());

        // then
        assertThat(findByEmailUser).isNotNull();
        assertThat(findByEmailUser.getId()).isEqualTo(user.getId());
        assertThat(findByEmailUser.getEmail()).isEqualTo(user.getEmail());


    }

    @Test
    @DisplayName("해당 이메일로 가입된 회원이 존재하지 않는 경우")
    void isNotExistUserFindByEmail() {

        // given
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.empty());

        // then
        assertThrows(UserNotFoundException.class, () -> {
            User findByEmailUser = generalUserService.findUserByEmail(user.getEmail());

        });
    }

    @Test
    @DisplayName("사용자 정보가 유효한 경우")
    void isValidUser() {

        // given
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        // then
        assertTrue(generalUserService.isValidUser(userDto, passwordEncoder));

    }

    @Test
    @DisplayName("사용자 정보가 유효하지 않은 경우")
    void isNotValidUser() {

        // given
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.of(user));

        // then
        assertFalse(generalUserService.isValidUser(userDto, passwordEncoder));


    }
}
