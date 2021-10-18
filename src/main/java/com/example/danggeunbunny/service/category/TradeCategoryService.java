package com.example.danggeunbunny.service.category;

import com.example.danggeunbunny.exception.board.CategoryNotFoundException;
import com.example.danggeunbunny.model.board.post.Category;
import com.example.danggeunbunny.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.example.danggeunbunny.config.cache.CacheKeyConfig.CATEGORY;

@Service
@RequiredArgsConstructor
public class TradeCategoryService implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable(key = "#categoryName", value = CATEGORY, cacheManager = "redisCacheManager", cacheNames = CATEGORY)
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByCategoryName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));

    }
}
