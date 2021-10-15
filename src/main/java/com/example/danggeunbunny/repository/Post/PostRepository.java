package com.example.danggeunbunny.repository.Post;

import com.example.danggeunbunny.model.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Optional<Post> findPostById(Long postId);
}
