package com.example.danggeunbunny.service.Post;

import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.dto.post.PostResponseDto;
import com.example.danggeunbunny.model.board.entity.Post;
import com.example.danggeunbunny.model.user.User;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    public void createNewPost(PostCreateRequestDto postCreateRequestDto, User user);

    public Post findPostById(Long postId);

    public void updatePost(Post post, PostCreateRequestDto postCreateRequestDto);

}

