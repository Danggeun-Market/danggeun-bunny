package com.example.danggeunbunny.service.Post;

import com.example.danggeunbunny.annotation.area.AreaInfoRequired;
import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.exception.post.PostNotFoundException;
import com.example.danggeunbunny.model.board.post.Category;
import com.example.danggeunbunny.model.board.post.Post;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.repository.Post.PostRepository;
import com.example.danggeunbunny.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TradePostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final CategoryService categoryService;


    @Override
    @AreaInfoRequired
    @Transactional
    public void createNewPost(PostCreateRequestDto postCreateRequestDto, User user) {

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
    public void updatePost(Post post, PostCreateRequestDto postCreateRequestDto) {

        Category category =  categoryService.findCategoryByName(postCreateRequestDto.getCategory());

        post.updatePost(postCreateRequestDto);
        post.setCategory(category);
    }
}

