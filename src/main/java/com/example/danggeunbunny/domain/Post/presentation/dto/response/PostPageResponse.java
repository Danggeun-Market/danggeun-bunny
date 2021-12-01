package com.example.danggeunbunny.domain.Post.presentation.dto.response;

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
    private List<PostResponse> postResponses = new ArrayList<>();

    public PostPageResponseDto(int totalPage, int currentPage, List<PostResponse> postResponses) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.postResponses = postResponses;
    }

}
