package com.example.danggeunbunny.domain.postsearch.domain.repository;

import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostSearchRepository extends JpaRepository<Feed, Long> {

    public Page<Feed> findAllByUserAddress(String state, String city, String town, String addressState, Pageable pageable);
}
