package com.example.danggeunbunny.domain.postsearch.service;

import com.example.global.dto.location.AddressRequestDto;
import com.example.danggeunbunny.domain.feed.presentation.dto.response.FeedPageResponse;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface FeedSearchService {

     FeedPageResponse findAllByUserAddress(User user, Pageable pageable);

     FeedPageResponse findAllByAddress(AddressRequestDto address, Pageable pageable);

     FeedPageResponse findALlByCategory(String category, User user, Pageable pageable);


}