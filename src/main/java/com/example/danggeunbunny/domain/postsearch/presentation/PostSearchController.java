package com.example.danggeunbunny.domain.postsearch.presentation;

import com.example.global.annotation.login.LoginRequired;
import com.example.global.annotation.login.LoginUser;
import com.example.danggeunbunny.domain.Post.presentation.dto.response.PostPageResponse;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.postsearch.service.TradePostSearchServiceImpl;
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
public class PostSearchController {

    private final TradePostSearchServiceImpl tradePostSearchService;

    @LoginRequired
    @GetMapping
    public ResponseEntity<PostPageResponse> getTradePosts(@LoginUser User user, Pageable pageable) {

        PostPageResponse page = tradePostSearchService.findAllByUserAddress(user, pageable);

        return ResponseEntity.ok(page);
    }

    @LoginRequired
    @GetMapping("/address")
    public ResponseEntity<PostPageResponse> getTradePostsByAddress(@Valid User user, Pageable pageable) {

        PostPageResponse page = tradePostSearchService.findAllByUserAddress(user, pageable);

        return ResponseEntity.ok(page);

    }

}
