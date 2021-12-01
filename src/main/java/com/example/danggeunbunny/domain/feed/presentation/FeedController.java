package com.example.danggeunbunny.domain.feed.presentation;

import com.example.danggeunbunny.domain.feed.presentation.dto.request.FeedCreateRequest;
import com.example.danggeunbunny.domain.feed.presentation.dto.response.FeedResponse;
import com.example.global.annotation.login.LoginRequired;
import com.example.global.annotation.login.LoginUser;
import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.feed.service.FeedService;
import com.example.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.global.error.ErrorCode.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
class FeedController {

    private final FeedService postService;
    private final ErrorCode httpStatusResponseEntity;

    /**
     * 게시물 생성 기능
     * @param postCreateRequestDto
     * @param user
     * @return
     */
    @LoginRequired
    @PostMapping
    public ErrorCode creatPost(@RequestBody @Valid FeedCreateRequest postCreateRequestDto, @LoginUser User user) {

        postService.createNewPost(postCreateRequestDto, user);

        return RESPONSE_OK;
    }

    @LoginRequired
    @GetMapping("/{postId}")
    public ResponseEntity<FeedResponse> findPost(@PathVariable Long postId) {

        return ResponseEntity.ok(FeedResponse.of(postService.findPostById(postId)));
    }

    @LoginRequired
    @PutMapping("/{postId}")
    public ErrorCode updatePost(@Valid @RequestBody FeedCreateRequest postCreateRequestDto, @PathVariable Long postId) {

        Feed post = postService.findPostById(postId);


        postService.updatePost(post, postCreateRequestDto);

            return RESPONSE_OK;
    }

    @LoginRequired
    @DeleteMapping("/{postId}")
    public ErrorCode deletePost(@PathVariable Long postId) {

        Feed post = postService.findPostById(postId);

        postService.removePost(post);
        return RESPONSE_OK;
    }

}
