package com.example.danggeunbunny.controller.user;

import com.example.danggeunbunny.annotation.login.LoginRequired;
import com.example.danggeunbunny.annotation.login.LoginUser;
import com.example.danggeunbunny.dto.profile.PasswordRequestDto;
import com.example.danggeunbunny.dto.profile.ProfileRequestDto;
import com.example.danggeunbunny.dto.profile.ProfileResponseDto;
import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.service.login.LoginService;
import com.example.danggeunbunny.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.example.danggeunbunny.util.HttpStatusResponseEntity.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final LoginService loginService;

    /**
     * 사용자 회원가입 경로
     * @param userDto
     * @return
     */
    @PostMapping
    private ResponseEntity<HttpStatus> registration(@RequestBody @Valid UserDto userDto){

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
    public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email) {
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
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid UserDto userDto) {

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
    public ResponseEntity<HttpStatus> logout() {
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
    private ResponseEntity<HttpStatus> changePassword(@LoginUser User user, @Valid @RequestBody PasswordRequestDto passwordRequestDto) {

        if (userService.isValidPassword(user, passwordRequestDto, passwordEncoder)) {
            userService.updateUserPassword(user, passwordRequestDto, passwordEncoder);
        }

        return RESPONSE_OK;
    }


}
