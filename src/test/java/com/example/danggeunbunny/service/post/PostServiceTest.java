package com.example.danggeunbunny.service.post;

import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.exception.post.PostNotFoundException;
import com.example.danggeunbunny.model.board.post.Category;
import com.example.danggeunbunny.model.board.post.Post;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.repository.Post.PostRepository;
import com.example.danggeunbunny.service.Post.TradePostServiceImpl;
import com.example.danggeunbunny.service.category.CategoryService;
import com.example.danggeunbunny.service.login.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private TradePostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private LoginService loginService;

    private User user;

    private Post post;

    private Category category;

    private PostCreateRequestDto postCreateRequestDto;

    @BeforeEach
    void setUp() {

        postCreateRequestDto = PostCreateRequestDto.builder()
                .title("노트북 맥북 프로 16인치 판매합니다.")
                .content("노트북을 파는 글")
                .category("디지털/가전")
                .build();

        user = User.builder()
                .email("daangnmarket@admin.com")
                .password("1q2w3e4r!")
                .nickname("김당근")
                .build();

        post = postCreateRequestDto.toEntity(user);
    }

    @Test
    @DisplayName("게시글이 성공적으로 등록될 경우 PostRepository.save(Post post), " +
            "CategoryService.findCategoryByName(String category) 메서드가 한번씩 호출.")
    void successToCreatePost() {
        // given
        when(categoryService.findCategoryByName(any())).thenReturn(category);

        // when
        postService.createNewPost(postCreateRequestDto, user);

        // then
        verify(postRepository, times(1)).save(any(Post.class));
        verify(categoryService, times(1)).findCategoryByName(anyString());
    }

    @Test
    @DisplayName("해당 아이디의 게시글이 존재하지 않으면 PostNotFoundException 예외를 발생시킴")
    void isNotExistPostFindById() {

        // given
        when(postRepository.findPostById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(PostNotFoundException.class, () -> {

            Post findByPostId = postService.findPostById(1L);
        });
    }

    @Test
    @DisplayName("해당 아이디의 게시글이 존재하는 경우 정상적으로 게시글을 조회.")
    void isExistPostFindById() {

        // given
        when(postRepository.findPostById(any())).thenReturn(Optional.of(post));

        // when
        Post findByPostId = postService.findPostById(post.getId());

        // then
        assertThat(findByPostId).isNotNull();
        assertThat(findByPostId.getId()).isEqualTo(post.getId());
        assertThat(findByPostId.getTitle()).isEqualTo(post.getTitle());
        assertThat(findByPostId.getContent()).isEqualTo(post.getContent());
    }
}
