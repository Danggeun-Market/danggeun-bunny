package com.example.danggeunbunny.service.Post;

import com.example.danggeunbunny.annotation.area.AreaInfoRequired;
import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.exception.post.PostNotFoundException;
import com.example.danggeunbunny.exception.user.UnAuthorizedAccessException;
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
    public void updatePost(Post post, PostCreateRequestDto postCreateRequestDto) {

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

