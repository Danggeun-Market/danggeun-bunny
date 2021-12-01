package com.example.danggeunbunny.domain.service.post;

import com.example.danggeunbunny.domain.feed.presentation.dto.request.FeedCreateRequest;
import com.example.danggeunbunny.domain.feed.exception.PostNotFoundException;
import com.example.danggeunbunny.domain.user.exception.UnAuthorizedAccessException;
import com.example.danggeunbunny.domain.category.Category;
import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.feed.domain.repository.FeedRepository;
import com.example.danggeunbunny.domain.feed.service.FeedServiceImpl;
import com.example.danggeunbunny.domain.category.service.CategoryService;
import com.example.danggeunbunny.domain.login.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private FeedServiceImpl postService;

    @Mock
    private FeedRepository postRepository;

    @Mock
    private CategoryService categoryService;

    @MocknService

    private User user;

    private Feed post;

    private Category category;

    private FeedCreateRequest postCreateRequestDto;

    @BeforeEach
    void setUp() {

        postCreateRequestDto = FeedCreateRequest.builder()
                .title("노트북 맥북 프로 16인치 판매합니다.")
                .content("노트북을 파는 글")
                .category("디지털/가전")
                .build();

        user = User.builder()
                .email("daangnmarket@admin.com")
                .password("1q2w3e4r!")
                .nickname("당근띠")
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
        verify(postRepository, times(1)).save(any(Feed.class));
        verify(categoryService, times(1)).findCategoryByName(anyString());
    }

    @Test
    @DisplayName("해당 아이디의 게시글이 존재하지 않으면 PostNotFoundException 예외를 발생시킴")
    void isNotExistPostFindById() {

        // given
        when(postRepository.findPostById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(PostNotFoundException.class, () -> {

            Feed findByPostId = postService.findPostById(1L);
        });
    }

    @Test
    @DisplayName("해당 아이디의 게시글이 존재하는 경우 정상적으로 게시글을 조회.")
    void isExistPostFindById() {

        // given
        when(postRepository.findPostById(any())).   thenReturn(Optional.of(post));

        // when
        Feed findByPostId = postService.findPostById(post.getId());

        // then
        assertThat(findByPostId).isNotNull();
        assertThat(findByPostId.getId()).isEqualTo(post.getId());
        assertThat(findByPostId.getTitle()).isEqualTo(post.getTitle());
        assertThat(findByPostId.getContent()).isEqualTo(post.getContent());
    }

    @Test
    @DisplayName("게시물이 성공적으로 업데이트 되는 경우 Post.updatePost(PostRequest request), " +
            "Post.setCategory(Category category), CategoryService.findCategoryByName(String catrgoey) 메서트 반환됨")
        void successToUpdatePost() {

        // given
        Feed post = mock(Feed.class);
        when(post.getAuthor()).thenReturn(user);
        when(categoryService.findCategoryByName(any())).thenReturn(category);
        when(loginService.getLoginUser()).thenReturn(user);

        // when
        postService.updatePost(post, postCreateRequestDto);

        // then
        verify(post, times(1)).updatePost(postCreateRequestDto);
        verify(post, times(1)).setCategory(category);
    }

    @Test
    @DisplayName("작성자가 일치하지 않을 경우 게시글 업데이트가 실패하고 UnAuthorizedAccessException이 발생")
    void isUnAuthorizedUserToUpdatePost() {

        // given
        User user = mock(User.class);
        when(loginService.getLoginUser()).thenReturn(user);

        // then
        assertThrows(UnAuthorizedAccessException.class, () -> {
            postService.updatePost(post, postCreateRequestDto);
        });
    }

    @Test
    @DisplayName("작성자가 일치할 경우 게시글 삭제에 성공하고 게시글의 removed가 true로 변경된다.")
    void successToRemovePost() {

        // given
        Feed post = mock(Feed.class);

        // when
        postService.removePost(post);

        // then
        assertThrows(UnAuthorizedAccessException.class, () -> {
            postService.removePost(post);
        });

    }

    @Test
    @DisplayName("작성자가 일치하지 않을 경우 게시글이 삭제에 실패하고 UnAuthroizedAccessException이 발생한다.")
    void isUnAuthorizedMemberToRemovePost() {
        // given
        User user = mock(User.class);

        when(loginService.getLoginUser()).thenReturn(user);

        // then
        assertThrows(UnAuthorizedAccessException.class, () -> {
            postService.removePost(post);
        });
    }

}
