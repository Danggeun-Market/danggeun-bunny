package com.example.danggeunbunny.domain.feed.service;

import com.example.danggeunbunny.global.annotation.area.AreaInfoRequired;
import com.example.danggeunbunny.domain.feed.presentation.dto.request.FeedCreateRequest;
import com.example.danggeunbunny.domain.feed.exception.PostNotFoundException;
import com.example.danggeunbunny.domain.user.exception.UnAuthorizedAccessException;
import com.example.danggeunbunny.domain.category.Category;
import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.feed.domain.repository.FeedRepository;
import com.example.danggeunbunny.domain.category.service.CategoryService;
import com.example.danggeunbunny.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository postRepository;
    private final CategoryService categoryService;
    private final LoginService loginService;


    @Override
    @AreaInfoRequired
    @Transactional
    public void createNewPost(FeedCreateRequest postCreateRequestDto, User user) {

        Feed post = postCreateRequestDto.toEntity(user);
        Category category = categoryService.findCategoryByName(postCreateRequestDto.getCategory());

        post.setCategory(category);

        postRepository.save(post);

    }

    @Override
    public Feed findPostById(Long postId) {

        return postRepository.findPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional
    public void updatePost(Feed post, FeedCreateRequest postCreateRequestDto) {

        if (isMatchedAuthor(post)) {
            Category category = categoryService.findCategoryByName(postCreateRequestDto.getCategory());


        post.updatePost(postCreateRequestDto);
        post.setCategory(category);

         }

    }

    @Override
    @Transactional
    public void removePost(Feed post) {

        if(isMatchedAuthor(post)) {
            post.removedPost();
        }

    }

    @Override
    public boolean isMatchedAuthor(Feed post) {

        User user = loginService.getLoginUser();

        if (post.getAuthor() != user) {
            try {
                throw new UnAuthorizedAccessException();

            } catch (UnAuthorizedAccessException e) {

                e.printStackTrace();
            }
        }

        return true;
    }
}

