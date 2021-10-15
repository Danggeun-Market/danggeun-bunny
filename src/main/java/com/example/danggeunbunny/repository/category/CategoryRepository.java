package com.example.danggeunbunny.repository.category;

import com.example.danggeunbunny.model.board.post.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Optional<Category> findCategoryByCategoryName(String categoryName);
}
