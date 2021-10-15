package com.example.danggeunbunny.dto.post;

import com.example.danggeunbunny.model.address.Address;
import com.example.danggeunbunny.model.address.Location;
import com.example.danggeunbunny.model.board.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class PostResponseDto {

    private final Long id;
    private final String title;
    private final String author;
    private final String email;
    private final String content;

    private final String status;
    private final String category;

    private final Address address;
    private final Location location;

    private final LocalDateTime createdTime;
    private final LocalDateTime modifiedTime;

    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
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