package com.example.danggeunbunny.domain.feed.service;

import com.example.danggeunbunny.domain.category.Category;
import com.example.danggeunbunny.domain.category.service.CategoryService;
import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import com.example.danggeunbunny.domain.feed.domain.repository.FeedRepository;
import com.example.danggeunbunny.domain.feed.exception.PostNotFoundException;
import com.example.danggeunbunny.domain.feed.presentation.dto.request.FeedCreateRequest;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.user.service.UserService;
import com.example.danggeunbunny.global.annotation.area.AreaInfoRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final CategoryService categoryService;
    private final UserService userService;


    @Override
    @AreaInfoRequired
    @Transactional
    public void createNewPost(FeedCreateRequest feedCreateRequest, User user) {

        Feed feed = feedCreateRequest.toEntity(user);
        Category category = categoryService.findCategoryByName(feedCreateRequest.getCategory());

        feed.setCategory(category);

        feedRepository.save(feed);

    }

    @Override
    public Feed findPostById(Long feedId) {

        return feedRepository.findPostById(feedId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional
    public void updatePost(Feed feed, FeedCreateRequest feedCreateRequest) {

        if (isMatchedAuthor(feed)) {
            Category category = categoryService.findCategoryByName(feedCreateRequest.getCategory());


        feed.updatePost(feedCreateRequest);
        feed.setCategory(category);

         }

    }

    @Override
    @Transactional
    public void removePost(Feed feed) {

        if(isMatchedAuthor(feed)) {
            feed.removedPost();
        }

    }

    @Override
    public boolean isMatchedAuthor(Feed post) {
        return false;
    }


}

