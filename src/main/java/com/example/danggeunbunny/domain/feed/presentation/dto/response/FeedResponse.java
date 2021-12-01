package com.example.danggeunbunny.domain.feed.presentation.dto.response;

import com.example.global.domain.entity.address.Address;
import com.example.global.domain.entity.address.Location;
import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FeedResponse {

    private Long id;
    private String title;
    private String author;
    private String email;
    private String content;

    private String status;
    private String category;

    private Address address;
    private Location location;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;


    public static FeedResponse of(Feed post) {
        return FeedResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getAuthor().getNickname())
                .email(post.getAuthor().getEmail())
                .content(post.getContent())
                .createdTime(post.getCreatedTime())
                .modifiedTime(post.getModifiedTime())
                .status(post.getStatus().getTradeStatus())
                .category(post.getCategory().getCategoryName())
                .address(post.getAddress())
                .location(post.getLocation())
                .build();

    }
}