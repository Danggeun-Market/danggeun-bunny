package com.example.danggeunbunny.dto.post;

import com.example.danggeunbunny.model.board.post.Post;
import com.example.danggeunbunny.model.board.post.TradeStatus;
import com.example.danggeunbunny.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequestDto {

    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 넘을 수 없습니다.")
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String category;

    public Post toEntity(User user) {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .author(user)
                .address(user.getAddress())
                .location(user.getLocation())
                .status(TradeStatus.SALE)
                .build();
    }

}
