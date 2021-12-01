package com.example.danggeunbunny.dto.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostPageResponseDto {

    private int totalPage;
    private int currentPage;
    private List<PostResponseDto> postResponses = new ArrayList<>();

    public PostPageResponseDto(int totalPage, int currentPage, List<PostResponseDto> postResponses) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.postResponses = postResponses;
    }

}
