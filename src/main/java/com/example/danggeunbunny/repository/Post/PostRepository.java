package com.example.danggeunbunny.repository.Post;

import com.example.danggeunbunny.model.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    @Query("select p from Post p where p.id = :postId and p.isRemoved = false")

    public Optional<Post> findPostById(Long postId);
}
