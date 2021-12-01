package com.example.danggeunbunny.domain.feed.domain.repository;

import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    
    @Query("select p from Feed p where p.id = :postId and p.isRemoved = false")

    public Optional<Feed> findPostById(Long postId);
}
