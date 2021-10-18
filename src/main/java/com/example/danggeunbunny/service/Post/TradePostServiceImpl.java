package com.example.danggeunbunny.service.Post;

import com.example.danggeunbunny.annotation.area.AreaInfoRequired;
import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.exception.post.PostNotFoundException;
import com.example.danggeunbunny.model.board.post.Category;
import com.example.danggeunbunny.model.board.post.Post;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.repository.Post.PostRepository;
import com.example.danggeunbunny.service.category.CategoryService;
import com.example.danggeunbunny.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TradePostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final LoginService loginService;


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
    public boolean updatePost(Post post, PostCreateRequestDto postCreateRequestDto) {

        User user = loginService.getLoginUser();

        if (post.getAuthor() != user) {
            return false;
        }
        Category category =  categoryService.findCategoryByName(postCreateRequestDto.getCategory());

        post.updatePost(postCreateRequestDto);
        post.setCategory(category);
        return true;

    }
}

