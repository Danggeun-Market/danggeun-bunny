package com.example.danggeunbunny.controller.postsearch;

import com.example.danggeunbunny.annotation.login.LoginRequired;
import com.example.danggeunbunny.annotation.login.LoginUser;
import com.example.danggeunbunny.dto.post.PostPageResponseDto;
import com.example.danggeunbunny.dto.post.PostResponseDto;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.service.postsearch.TradePostSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/searches")
public class PostSearchController {

    private final TradePostSearchServiceImpl tradePostSearchService;

    @LoginRequired
    @GetMapping
    public ResponseEntity<PostPageResponseDto> getTradePosts(@LoginUser User user, Pageable pageable) {

        PostPageResponseDto page = tradePostSearchService.findAllUserAddress(user, pageable);

        return ResponseEntity.ok(page);
    }

}
