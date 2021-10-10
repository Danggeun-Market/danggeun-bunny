package com.example.danggeunbunny.controller.user;

import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.service.login.LoginService;
import com.example.danggeunbunny.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }

    /**
     * 사용자 로그아웃 기능
     * @return
     */


    @GetMapping("/logout")
    public ResponseEntity<HttpStatus> logout() {
        loginService.logout();

        return RESPONSE_OK;
    }

}
