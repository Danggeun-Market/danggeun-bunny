package com.example.danggeunbunny.domain.feed.service;

import com.example.danggeunbunny.domain.feed.presentation.dto.request.FeedCreateRequest;
import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface FeedService {

     void createNewPost(FeedCreateRequest postCreateRequestDto, User user);

     Feed findPostById(Long postId);

     void updatePost(Feed post, FeedCreateRequest postCreateRequestDto);

     void removePost(Feed post);

     boolean isMatchedAuthor(Feed post);
}

