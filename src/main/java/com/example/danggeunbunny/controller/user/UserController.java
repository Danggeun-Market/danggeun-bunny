package com.example.danggeunbunny.controller.user;

import com.example.danggeunbunny.model.user.UserRequest;
import com.example.danggeunbunny.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_CONFLICT;
import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * 사용자 회원가입 경루
     * @param userRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid UserRequest userRequest) {
        userService.registrationUser(userRequest);

        return RESPONSE_OK;
    }

    /**
     * 사용자 이메일 중복체크 사용
     * @param email
     * @return
     */
    @GetMapping("/duplicated/{email}")
    public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email) {
        boolean isDuplicated = userService.isDuplicatedEMail(email);

        if(isDuplicated) {
            return RESPONSE_CONFLICT;
        }

        return RESPONSE_OK;
    }
}
