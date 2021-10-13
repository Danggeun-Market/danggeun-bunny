package com.example.danggeunbunny.service.user;

import com.example.danggeunbunny.dto.location.LocationRequestDto;
import com.example.danggeunbunny.dto.profile.PasswordRequestDto;
import com.example.danggeunbunny.dto.profile.ProfileRequestDto;
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

    private PasswordRequestDto passwordRequestDto;

    private ProfileRequestDto profileRequestDto;

    private LocationRequestDto locationRequestDto;

    @BeforeEach
    void setUp() {
        when(passwordEncoder.encode(any())).thenReturn("pillar0030!");
        userDto = UserDto.builder()
                .email("lyutvs@gmail.com")
                .password("pillar0030!")
                .nickname("엄청난노옴")
                .build();

        passwordRequestDto = new PasswordRequestDto("pillar0030!", "PILLAR))#)!");

        profileRequestDto = new ProfileRequestDto("lyutvs@gmail.com", "엄청난노옴");

        locationRequestDto = LocationRequestDto.builder()
                .state("대전광역시")
                .city("여성구")
                .town("중구")
                .longitude(126.94250287828)
                .latitude(37.4853777674734)
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

    @Test
    @DisplayName("변경전 패스워드를 올바르게 입력한 경우")
    void isValidOldPassword() {

        // given
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        // then
        assertTrue(generalUserService.isValidPassword(user, passwordRequestDto, passwordEncoder));

    }

    @Test
    @DisplayName("변경전 패스워드를 다르게 입력한 경우")
    void isNotValidPassword() {

        // given
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        // then
        assertFalse(generalUserService.isValidPassword(user, passwordRequestDto, passwordEncoder));
    }

    @Test
    @DisplayName("사용자 프로필 변경 성공한 경우")
    void successToUpdateUserProfile() {

        // when
        generalUserService.updateUserProfile(user, profileRequestDto);

        // then
        assertEquals(user.getNickname(), profileRequestDto.getNickname());

    }

    @Test
    @DisplayName("사용자 패스워드 변경에 성공한 경우")
    void successToUpdatePassword() {

        // given
        when(passwordEncoder.encode(any())).thenReturn(passwordRequestDto.getNewPassword());

        // when
        generalUserService.updateUserPassword(user, passwordRequestDto, passwordEncoder);

        // then
        assertEquals(user.getPassword(), passwordRequestDto.getOldPassword());

    }

    @Test
    @DisplayName("사용자 위치 정보 등록에 성공한 경우")
    void successToUpdateUserLocationAndAddress() {

        // whenn
        generalUserService.setUserLocationAddress(user, locationRequestDto);

        // then
        assertThat(user.getAddress()).isNotNull();
        assertThat(user.getLocation()).isNotNull();
        assertEquals(user.getAddress().getState(), locationRequestDto.getState());
        assertEquals(user.getAddress().getCity(), locationRequestDto.getCity());
        assertEquals(user.getAddress().getTown(), locationRequestDto.getTown());
        assertEquals(user.getLocation().getLongitude(), locationRequestDto.getLongitude());
        assertEquals(user.getLocation().getLatitude(), locationRequestDto.getLatitude());
    }
}
