package com.example.danggeunbunny.domain.category.service;

import com.example.danggeunbunny.domain.Post.domain.entity.Category;

public interface CategoryService {

     Category findCategoryByName(String categoryName);
}
