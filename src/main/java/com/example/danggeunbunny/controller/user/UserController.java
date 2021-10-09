package com.example.danggeunbunny.controller.user;

import com.example.danggeunbunny.model.user.UserRequest;
import com.example.danggeunbunny.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
