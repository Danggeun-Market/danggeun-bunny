package com.example.danggeunbunny.domain.Post.service;

import com.example.global.annotation.area.AreaInfoRequired;
import com.example.danggeunbunny.domain.Post.presentation.dto.request.PostCreateRequest;
import com.example.danggeunbunny.domain.Post.exception.PostNotFoundException;
import com.example.danggeunbunny.domain.user.exception.UnAuthorizedAccessException;
import com.example.danggeunbunny.domain.Post.domain.entity.Category;
import com.example.danggeunbunny.domain.Post.domain.entity.Post;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.Post.domain.repository.PostRepository;
import com.example.danggeunbunny.domain.category.service.CategoryService;
import com.example.danggeunbunny.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TradePostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final LoginService loginService;


    @Override
    @AreaInfoRequired
    @Transactional
    public void createNewPost(PostCreateRequest postCreateRequestDto, User user) {

        Post post = postCreateRequestDto.toEntity(user);
        Category category = categoryService.findCategoryByName(postCreateRequestDto.getCategory());

        post.setCategory(category);

        postRepository.save(post);

    }

    @Override
    public Post findPostById(Long postId) {

        return postRepository.findPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional
    public void updatePost(Post post, PostCreateRequest postCreateRequestDto) {

        if (isMatchedAuthor(post)) {
            Category category = categoryService.findCategoryByName(postCreateRequestDto.getCategory());


        post.updatePost(postCreateRequestDto);
        post.setCategory(category);

         }

    }

    @Override
    @Transactional
    public void removePost(Post post) {

        if(isMatchedAuthor(post)) {
            post.removedPost();
        }

    }

    @Override
    public boolean isMatchedAuthor(Post post) {

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

