package com.example.danggeunbunny.service.login;

import com.example.danggeunbunny.exception.user.UserNotFoundException;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.service.user.GeneralUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    private SessionLoginService loginService;

    @Mock
    private GeneralUserServiceImpl generalUserService;

    protected MockHttpSession mockHttpSession;

    private static final Long MEMBER_ID = 1L;

    private User user;

    @BeforeEach
    void setup() {

        user = user.builder()
                .email("lyutvs@gail.com")
                .nickname("엄청난노옴")
                .password("pillar0030!")
                .build();

        mockHttpSession = new MockHttpSession();

        loginService = new SessionLoginService(mockHttpSession, generalUserService);
    }

    @Test
    @DisplayName("사용자 로그인 성공 테스트")
    void successToLogin() {

        // when
        loginService.login(MEMBER_ID);

        // when
        assertThat(mockHttpSession.getAttribute("MEMBER_ID")).isNotNull();

        assertThat(mockHttpSession.getAttribute("MEMBER_ID")).isEqualTo(1L);

    }

    @Test
    @DisplayName("사용자 로그아웃 성고 테스트")
    void successToLogout() {

        // when
        loginService.logout();

        // then
        assertThat(mockHttpSession.getAttribute("MEMBER_ID")).isNotNull();

    }

    @Test
    @DisplayName("로그인 사용자 조회 성공 테스트")
    void isExistLoginUser() {

        // given
        mockHttpSession.setAttribute("MEMBER_ID", MEMBER_ID);
        when(generalUserService.findUserById(anyLong())).thenReturn(user);

        // when
        User loginUser = loginService.getLoginUser();

        // then
        assertThat(loginUser).isNotNull();

    }

    @Test
    @DisplayName("호그인 사용자 조회 실패 테스트")
    void isNotExistLoginUser() {

        // given
        mockHttpSession.setAttribute("EMBER_ID", 2L);

        // then
        assertThrows(UserNotFoundException.class, () -> {
            loginService.getLoginUser();
        });
    }
}
