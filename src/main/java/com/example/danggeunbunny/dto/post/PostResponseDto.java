package com.example.danggeunbunny.dto.post;

import com.example.danggeunbunny.model.address.Address;
import com.example.danggeunbunny.model.address.Location;
import com.example.danggeunbunny.model.board.post.Post;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostResponseDto {

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

    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
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