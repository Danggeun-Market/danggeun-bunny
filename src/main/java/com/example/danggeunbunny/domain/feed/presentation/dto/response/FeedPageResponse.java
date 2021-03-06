package com.example.danggeunbunny.domain.feed.presentation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedPageResponse {

    private int totalPage;
    private int currentPage;
    private List<FeedResponse> postResponses = new ArrayList<>();

    public FeedPageResponse(int totalPage, int currentPage, List<FeedResponse> postResponses) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.postResponses = postResponses;
    }

}
