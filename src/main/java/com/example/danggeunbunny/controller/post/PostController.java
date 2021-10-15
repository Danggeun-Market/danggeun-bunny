package com.example.danggeunbunny.controller.post;

import com.example.danggeunbunny.annotation.login.LoginRequired;
import com.example.danggeunbunny.annotation.login.LoginUser;
import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.dto.post.PostResponseDto;
import com.example.danggeunbunny.model.board.entity.Post;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.service.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_OK;
import static com.example.danggeunbunny.util.HttpStatusResponseEntity.RESPONSE_UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    /**
     * 게시물 생성 기능
     * @param postCreateRequestDto
     * @param user
     * @return
     */
    @LoginRequired
    @PostMapping
    public ResponseEntity<HttpStatus> creatPost(@RequestBody @Valid PostCreateRequestDto postCreateRequestDto, @LoginUser User user) {

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
    public ResponseEntity<HttpStatus> updatePost(@Valid @RequestBody PostCreateRequestDto postCreateRequestDto, @PathVariable Long postId, @LoginUser User user) {

        Post post = postService.findPostById(postId);

        if (post.getAuthor() != user) {

            return RESPONSE_UNAUTHORIZED;
        }

        postService.updatePost(post, postCreateRequestDto);

        return RESPONSE_OK;
    }

}