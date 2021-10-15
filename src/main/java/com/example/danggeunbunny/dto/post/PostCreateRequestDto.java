package com.example.danggeunbunny.dto.post;

import com.example.danggeunbunny.model.board.entity.Category;
import com.example.danggeunbunny.model.board.entity.Post;
import com.example.danggeunbunny.model.board.entity.TradeStatus;
import com.example.danggeunbunny.model.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor
public class PostCreateRequestDto {

    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 넘을 수 없습니다.")
    private final String title;

    @NotEmpty
    private final String content;

    @NotEmpty
    private final String category;

    public Post toEntity(User user) {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .category(Category.valueOf(this.category))
                .author(user)
                .address(user.getAddress())
                .location(user.getLocation())
                .status(TradeStatus.SALE)
                .build();
    }

}
