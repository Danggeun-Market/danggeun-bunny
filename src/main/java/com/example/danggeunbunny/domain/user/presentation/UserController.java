package com.example.danggeunbunny.domain.user.presentation;

import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.user.presentation.dto.UserDto;
import com.example.danggeunbunny.global.annotation.login.LoginRequired;
import com.example.danggeunbunny.global.annotation.login.LoginUser;
import com.example.danggeunbunny.global.dto.Request.LocationRequest;
import com.example.danggeunbunny.domain.user.presentation.dto.request.PasswordRequestDto;
import com.example.danggeunbunny.domain.user.presentation.dto.request.ProfileRequestDto;
import com.example.danggeunbunny.domain.user.presentation.dto.request.ProfileResponseDto;
import com.example.danggeunbunny.domain.login.service.LoginService;
import com.example.danggeunbunny.domain.user.service.UserService;
import com.example.danggeunbunny.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.danggeunbunny.global.error.ErrorCode.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    public static final String USER_API_URI = "/api/user";

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final LoginService loginService;

    /**
     * 사용자 회원가입 경로
     * @param userDto
     * @return
     */
    @PostMapping
    private ErrorCode registration(@RequestBody @Valid UserDto userDto){

        // 클라이언트에서 사용자 이메일 중복체크를 수행하지만 API요청에 의한 예외상황에 대비하여 더블체크
        boolean isDuplicated = userService.isDuplicatedEmail(userDto.getEmail());

        if(isDuplicated) {
            return RESPONSE_CONFLICT;
        }


        User user = UserDto.toEntity(userDto, passwordEncoder);
        userService.registrationUser(user);

        return RESPONSE_OK;
    }

    /**
     * 사용자 이메일 중복체크 사용
     * @param email
     * @return
     */
    @GetMapping("/duplicated/{email}")
    public ErrorCode isDuplicatedEmail(@PathVariable String email) {
        boolean isDuplicated = userService.isDuplicatedEmail(email);

        if(isDuplicated) {
            return RESPONSE_CONFLICT;
        }

        return RESPONSE_OK;
    }

    /**
     * 사용자 로그인
     * @param userDto
     * @return
     */
    @PostMapping("/login")
    public ErrorCode login(@RequestBody @Valid UserDto userDto) {

        boolean isValidMember = userService.isValidUser(userDto, passwordEncoder);

        if (isValidMember) {
            loginService.login(userService.findUserByEmail(userDto.getEmail()).getId());

            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }

    /**
     * 사용자 로그아웃 기능
     * @return
     */

    @LoginRequired
    @GetMapping("/logout")
    public ErrorCode logout() {
        loginService.logout();

        return RESPONSE_OK;
    }

    /**
     * 사용자 프로필 조회 기능
     * @return
     */
    @LoginRequired
    @GetMapping("/my-profile")
    private ResponseEntity<ProfileResponseDto> getUserProfile(@LoginUser User user) {

        return ResponseEntity.ok(ProfileResponseDto.of(user));
    }

    /**
     * 사용자 정보 업데이트
     * @param profileRequestDto
     * @return
     */
    @LoginRequired
    @PostMapping("/my-profile")
    public ResponseEntity<ProfileResponseDto> updateUserProfile(@LoginUser User user, @RequestBody ProfileRequestDto profileRequestDto) {


        userService.updateUserProfile(user, profileRequestDto);

        return ResponseEntity.ok(ProfileResponseDto.of(user));
    }

    /**
     * 사용자 비밀번호 변경 기능
     * @param passwordRequestDto
     * @return
     */
    @LoginRequired
    @PutMapping("/password")
    public ErrorCode changePassword(@LoginUser User user, @Valid @RequestBody PasswordRequestDto passwordRequestDto) {

        if (userService.isValidPassword(user, passwordRequestDto, passwordEncoder)) {
            userService.updateUserPassword(user, passwordRequestDto, passwordEncoder);
        }

        return RESPONSE_OK;
    }

    /**
     * 사용자 위치 등록 기능
     * @param user
     * @param locationRequestDto
     * @return
     */
    @LoginRequired
    @PutMapping("/my-location")
    public ErrorCode setUserLocationAddress(@LoginUser User user, @RequestBody LocationRequest locationRequestDto) {

        userService.setUserLocationAddress(user, locationRequestDto);

        return RESPONSE_OK;

    }


}
