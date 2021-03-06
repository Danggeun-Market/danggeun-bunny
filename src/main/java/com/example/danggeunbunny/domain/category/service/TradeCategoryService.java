package com.example.danggeunbunny.domain.category.service;

import com.example.danggeunbunny.domain.feed.exception.CategoryNotFoundException;
import com.example.danggeunbunny.domain.category.Category;
import com.example.danggeunbunny.domain.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.example.danggeunbunny.global.cache.CacheKeyConfig.CATEGORY;

@Service
@RequiredArgsConstructor
public class TradeCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable(key = "#categoryName", value = CATEGORY, cacheNames = CATEGORY)
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByCategoryName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));

    }
}
