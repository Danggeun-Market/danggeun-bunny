package com.example.danggeunbunny.domain.postsearch.presentation;

import com.example.danggeunbunny.global.annotation.login.LoginRequired;
import com.example.danggeunbunny.global.annotation.login.LoginUser;
import com.example.danggeunbunny.domain.feed.presentation.dto.response.FeedPageResponse;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.postsearch.service.FeedSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/searches")
public class FeedController {

    private final FeedSearchServiceImpl tradePostSearchService;

    @LoginRequired
    @GetMapping
    public ResponseEntity<FeedPageResponse> getTradePosts(@LoginUser User user, Pageable pageable) {

        FeedPageResponse page = tradePostSearchService.findAllByUserAddress(user, pageable);

        return ResponseEntity.ok(page);
    }

    @LoginRequired
    @GetMapping("/address")
    public ResponseEntity<FeedPageResponse> getTradePostsByAddress(@Valid User user, Pageable pageable) {

        FeedPageResponse page = tradePostSearchService.findAllByUserAddress(user, pageable);

        return ResponseEntity.ok(page);

    }

}
