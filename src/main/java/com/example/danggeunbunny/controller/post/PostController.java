package com.example.danggeunbunny.controller.post;

import com.example.danggeunbunny.annotation.login.LoginRequired;
import com.example.danggeunbunny.annotation.login.LoginUser;
import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.service.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @LoginRequired
    @PostMapping
    public ResponseEntity<HttpStatus> creatNewPost(@RequestBody @Valid PostCreateRequestDto postCreateRequestDto, @LoginUser User user) {

        postService.createNewPost(postCreateRequestDto, user);

        return RESPONSE_OK;
    }
}
