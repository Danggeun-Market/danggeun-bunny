package com.example.danggeunbunny.domain.feed.domain.entity;

import com.example.danggeunbunny.domain.category.Category;
import com.example.danggeunbunny.domain.feed.presentation.dto.request.FeedCreateRequest;
import com.example.global.domain.entity.address.Address;
import com.example.global.domain.entity.address.Location;
import com.example.global.domain.repository.BaseTimeEntity;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Enumerated(EnumType.STRING)
    private TradeStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private User author;

    @Lob
    private String content;

    @Embedded
    private Address address;

    @Embedded
    private Location location;

    @Column(name = "IS_REMOVED")
    private boolean isRemoved = false;

    @Builder
    public Feed(String title, TradeStatus status, User author,
                String content, Address address, Location location) {
        this.title = title;
        this.status = status;
        this.author = author;
        this.content = content;
        this.address = address;
        this.location = location;
    }

    @Builder
    public Feed(Long id, String title, Category category, TradeStatus status,
                User author, String content, Address address, Location location,
                LocalDateTime createdTime, LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.status = status;
        this.author = author;
        this.content = content;
        this.address = address;
        this.location = location;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    public void updatePost(FeedCreateRequest postCreateRequestDto) {
        this.title = postCreateRequestDto.getTitle();
        this.content = postCreateRequestDto.getContent();
    }

    public void removedPost() {
        this.isRemoved = true;
    }

}
