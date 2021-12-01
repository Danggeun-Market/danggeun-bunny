package com.example.danggeunbunny.domain.postsearch.service;

import com.example.global.annotation.area.AreaInfoRequired;
import com.example.global.dto.location.AddressRequestDto;
import com.example.danggeunbunny.domain.Post.presentation.dto.response.PostPageResponse;
import com.example.danggeunbunny.domain.Post.presentation.dto.response.PostResponse;
import com.example.global.domain.entity.address.Address;
import com.example.danggeunbunny.domain.Post.domain.entity.Post;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import com.example.danggeunbunny.domain.postsearch.domain.repository.PostSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradePostSearchServiceImpl implements PostSearchService {

    private final PostSearchRepository postSearchRepository;

    @Override
    @AreaInfoRequired
    public PostPageResponse findAllByUserAddress(User user, Pageable pageable) {
        Address address = user.getAddress();
        Page<Post> posts = postSearchRepository.findAllByUserAddress(address.getState(),address.getCity(), address.getTown(), address.getState(), pageable);

        return getPostPageResponse(posts,pageable);

    }


    @Override
    public PostPageResponse findAllByAddress(AddressRequestDto address, Pageable pageable) {

        Page<Post> posts = postSearchRepository.findAllByUserAddress(address.getState(), address.getCity(), address.getCity(), address.getState(), pageable);

        List<PostResponse> postResponseDtos = posts.getContent().stream().map(PostResponse::of).collect(Collectors.toList());

        return PostPageResponse.builder()
                .totalPage(posts.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .postResponses(postResponseDtos)
                .build();
    }

    @Override
    @AreaInfoRequired
    public PostPageResponse findALlByCategory(String category, User user, Pageable pageable) {

        Address address = user.getAddress();
        Page<Post> posts = postSearchRepository.findAllByUserAddress(category, address.getCity(), address.getTown(), address.getState(), pageable);

        return getPostPageResponse(posts, pageable);
    }

    private PostPageResponse getPostPageResponse(Page<Post> posts, Pageable pageable) {

        List<PostResponse> postResponseDtos = posts.getContent().stream().map(PostResponse::of).collect(Collectors.toList());

        return PostPageResponse.builder()
                .totalPage(posts.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .postResponses(postResponseDtos)
                .build();
    }

}
