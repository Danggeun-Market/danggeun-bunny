package com.example.danggeunbunny.domain.postsearch.domain.repository;

import com.example.danggeunbunny.domain.Post.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostSearchRepository extends JpaRepository<Post, Long> {

    public Page<Post> findAllByUserAddress(String state, String city, String town, String addressState, Pageable pageable);
}
