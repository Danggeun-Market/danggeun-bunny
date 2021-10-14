package com.example.danggeunbunny.model.board.entity;

import com.example.danggeunbunny.model.address.Address;
import com.example.danggeunbunny.model.address.Location;
import com.example.danggeunbunny.model.board.BaseTimeEntity;
import com.example.danggeunbunny.model.user.User;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
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
    @Builder
    public Post(String title, Category category, TradeStatus status, User author,
                String content, Address address, Location location) {
        this.title = title;
        this.category = category;
        this.status = status;
        this.author = author;
        this.content = content;
        this.address = address;
        this.location = location;
    }



}
