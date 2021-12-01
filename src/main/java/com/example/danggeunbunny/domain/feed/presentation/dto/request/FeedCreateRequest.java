package com.example.danggeunbunny.domain.feed.presentation.dto.request;

import com.example.danggeunbunny.domain.feed.domain.entity.Feed;
import com.example.danggeunbunny.domain.feed.domain.entity.TradeStatus;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FeedCreateRequest {

    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 넘을 수 없습니다.")
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String category;

    public Feed toEntity(User user) {
        return Feed.builder()
                .title(this.title)
                .content(this.content)
                .author(user)
                .address(user.getAddress())
                .location(user.getLocation())
                .status(TradeStatus.SALE)
                .build();
    }

}
