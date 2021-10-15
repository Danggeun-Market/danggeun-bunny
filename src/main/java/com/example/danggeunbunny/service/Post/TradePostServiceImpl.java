package com.example.danggeunbunny.service.Post;

import com.example.danggeunbunny.annotation.area.AreaInfoRequired;
import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.exception.board.CategoryNotFoundException;
import com.example.danggeunbunny.exception.post.PostNotFoundException;
import com.example.danggeunbunny.model.board.entity.Category;
import com.example.danggeunbunny.model.board.entity.Post;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.repository.Post.PostRepository;
import com.example.danggeunbunny.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TradePostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;


    @Override
    @AreaInfoRequired
    @Transactional
    public void createNewPost(PostCreateRequestDto postCreateRequestDto, User user) {

        Post post = postCreateRequestDto.toEntity(user);
        Category category = categoryRepository.findCategoryByCategoryName(

                postCreateRequestDto.getCategory()).orElseThrow (() -> new CategoryNotFoundException(postCreateRequestDto.getCategory()));

        post.setCategory(category);

        postRepository.save(post);

    }

    @Override
    public Post findPostById(Long postId) {

        return postRepository.findPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional
    public void updatePost(Post post, PostCreateRequestDto postCreateRequestDto) {

        Category category = categoryRepository.findCategoryByCategoryName(
                postCreateRequestDto.getCategory()).orElseThrow (() -> new CategoryNotFoundException(postCreateRequestDto.getCategory()));

        post.updatePost(postCreateRequestDto);
        post.setCategory(category);
    }
}
