package com.example.danggeunbunny.domain.Post.presentation;

import com.example.danggeunbunny.domain.Post.presentation.dto.request.PostCreateRequest;
import com.example.danggeunbunny.domain.Post.presentation.dto.response.PostResponse;
import com.example.global.annotation.login.LoginRequired;
import com.example.global.annotation.login.LoginUser;
import com.example.danggeunbunny.domain.Post.domain.entity.Post;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.Post.service.PostService;
import com.example.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.global.error.ErrorCode.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final ErrorCode httpStatusResponseEntity;

    /**
     * 게시물 생성 기능
     * @param postCreateRequestDto
     * @param user
     * @return
     */
    @LoginRequired
    @PostMapping
    public ErrorCode creatPost(@RequestBody @Valid PostCreateRequest postCreateRequestDto, @LoginUser User user) {

        postService.createNewPost(postCreateRequestDto, user);

        return RESPONSE_OK;
    }

    @LoginRequired
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> findPost(@PathVariable Long postId) {

        return ResponseEntity.ok(PostResponse.of(postService.findPostById(postId)));
    }

    @LoginRequired
    @PutMapping("/{postId}")
    public ErrorCode updatePost(@Valid @RequestBody PostCreateRequest postCreateRequestDto, @PathVariable Long postId) {

        Post post = postService.findPostById(postId);


        postService.updatePost(post, postCreateRequestDto);

            return RESPONSE_OK;
    }

    @LoginRequired
    @DeleteMapping("/{postId}")
    public ErrorCode deletePost(@PathVariable Long postId) {

        Post post = postService.findPostById(postId);

        postService.removePost(post);
        return RESPONSE_OK;
    }

}
