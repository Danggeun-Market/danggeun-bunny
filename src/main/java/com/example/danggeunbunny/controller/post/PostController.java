package com.example.danggeunbunny.controller.post;

import com.example.danggeunbunny.annotation.login.LoginRequired;
import com.example.danggeunbunny.annotation.login.LoginUser;
import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.dto.post.PostResponseDto;
import com.example.danggeunbunny.model.board.post.Post;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.service.Post.PostService;
import com.example.danggeunbunny.util.HttpStatusResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final HttpStatusResponseEntity httpStatusResponseEntity;

    /**
     * 게시물 생성 기능
     * @param postCreateRequestDto
     * @param user
     * @return
     */
    @LoginRequired
    @PostMapping
    public HttpStatusResponseEntity creatPost(@RequestBody @Valid PostCreateRequestDto postCreateRequestDto, @LoginUser User user) {

        postService.createNewPost(postCreateRequestDto, user);

        return RESPONSE_OK;
    }

    @LoginRequired
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> findPost(@PathVariable Long postId) {

        return ResponseEntity.ok(PostResponseDto.of(postService.findPostById(postId)));
    }

    @LoginRequired
    @PutMapping("/{postId}")
    public HttpStatusResponseEntity updatePost(@Valid @RequestBody PostCreateRequestDto postCreateRequestDto, @PathVariable Long postId) {

        Post post = postService.findPostById(postId);


        postService.updatePost(post, postCreateRequestDto);

            return RESPONSE_OK;
    }

    @LoginRequired
    @DeleteMapping("/{postId}")
    public HttpStatusResponseEntity deletePost(@PathVariable Long postId) {

        Post post = postService.findPostById(postId);

        postService.removePost(post);
        return RESPONSE_OK;
    }

}
