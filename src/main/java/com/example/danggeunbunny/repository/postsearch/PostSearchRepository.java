package com.example.danggeunbunny.repository.postsearch;

import com.example.danggeunbunny.model.board.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PostSearchRepository extends JpaRepository<Post, Long> {

    public Page<Post> findAllByUserAddress(String state, String city, String town, Pageable pageable);
}
