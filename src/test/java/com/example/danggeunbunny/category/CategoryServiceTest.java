package com.example.danggeunbunny.category;

import com.example.danggeunbunny.domain.feed.exception.CategoryNotFoundException;
import com.example.danggeunbunny.domain.category.Category;
import com.example.danggeunbunny.domain.category.domain.repository.CategoryRepository;
import com.example.danggeunbunny.domain.category.service.TradeCategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private TradeCategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("존재하지 않는 카테고리명을 조회할 경우 CategoryNotFoundException이 발생한다.")
    void isNotExistCategoryFindByName() {

        //given
        when(categoryRepository.findCategoryByCategoryName(anyString())).thenReturn(Optional.empty());

        // then
        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.findCategoryByName(anyString());
        });
    }

    @Test
    @DisplayName("정상적으로 존재하는 카테고리명을 조회할 경우 성공적으로 조회한 Category 클래스를 반한")
    void isExistCategoryFindByName() {

        // given
        Category category = mock(Category.class);
        when(categoryRepository.findCategoryByCategoryName(anyString())).thenReturn(Optional.empty());

        // when
        Category findCategory = categoryService.findCategoryByName(anyString());

        // then
        assertThat(findCategory).isNotNull();
        assertThat(findCategory).isEqualTo(category);
        verify(categoryRepository, times(1)).findCategoryByCategoryName(anyString());

    }
}
