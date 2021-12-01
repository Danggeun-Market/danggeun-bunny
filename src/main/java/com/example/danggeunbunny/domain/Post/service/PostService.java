package com.example.danggeunbunny.domain.Post.service;

import com.example.danggeunbunny.domain.Post.presentation.dto.request.PostCreateRequest;
import com.example.danggeunbunny.domain.Post.domain.entity.Post;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

     void createNewPost(PostCreateRequest postCreateRequestDto, User user);

     Post findPostById(Long postId);

     void updatePost(Post post, PostCreateRequest postCreateRequestDto);

     void removePost(Post post);

     boolean isMatchedAuthor(Post post);
}

