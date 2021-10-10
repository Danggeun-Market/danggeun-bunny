package com.example.danggeunbunny.controller.user;

import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.model.user.User;
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

    private static final String MEMBER_ID = "MEMBER_ID";
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 회원가입 경루
     * @param userDto
     * @return
     */
    @PostMapping
    private ResponseEntity<HttpStatus> registration(@RequestBody @Valid UserDto userDto){

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

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid UserDto userDto, HttpSession httpSession) {

        User user = userService.findUserByEmail(userDto.getEmail());

        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

            httpSession.setAttribute(MEMBER_ID, user.getId());
            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }
}
