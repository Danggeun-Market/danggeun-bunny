package com.example.danggeunbunny.domain.postsearch.service;

import com.example.danggeunbunny.global.annotation.area.AreaInfoRequired;
import com.example.danggeunbunny.global.dto.Request.AddressRequest;
import com.example.danggeunbunny.domain.feed.presentation.dto.response.FeedPageResponse;
import com.example.danggeunbunny.domain.feed.presentation.dto.response.FeedResponse;
import com.example.danggeunbunny.global.domain.entity.address.Address;
import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.postsearch.domain.repository.PostSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedSearchServiceImpl implements FeedSearchService {

    private final PostSearchRepository postSearchRepository;

    @Override
    @AreaInfoRequired
    public FeedPageResponse findAllByUserAddress(User user, Pageable pageable) {
        Address address = user.getAddress();
        Page<Feed> posts = postSearchRepository.findAllByUserAddress(address.getState(),address.getCity(), address.getTown(), address.getState(), pageable);

        return getPostPageResponse(posts,pageable);

    }


    @Override
    public FeedPageResponse findAllByAddress(AddressRequest address, Pageable pageable) {

        Page<Feed> posts = postSearchRepository.findAllByUserAddress(address.getState(), address.getCity(), address.getCity(), address.getState(), pageable);

        List<FeedResponse> postResponseDtos = posts.getContent().stream().map(FeedResponse::of).collect(Collectors.toList());

        return FeedPageResponse.builder()
                .totalPage(posts.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .postResponses(postResponseDtos)
                .build();
    }

    @Override
    @AreaInfoRequired
    public FeedPageResponse findALlByCategory(String category, User user, Pageable pageable) {

        Address address = user.getAddress();
        Page<Feed> posts = postSearchRepository.findAllByUserAddress(category, address.getCity(), address.getTown(), address.getState(), pageable);

        return getPostPageResponse(posts, pageable);
    }

    private FeedPageResponse getPostPageResponse(Page<Feed> posts, Pageable pageable) {

        List<FeedResponse> postResponseDtos = posts.getContent().stream().map(FeedResponse::of).collect(Collectors.toList());

        return FeedPageResponse.builder()
                .totalPage(posts.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .postResponses(postResponseDtos)
                .build();
    }

}
