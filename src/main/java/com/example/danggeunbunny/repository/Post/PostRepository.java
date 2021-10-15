package com.example.danggeunbunny.repository.Post;

import com.example.danggeunbunny.model.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
