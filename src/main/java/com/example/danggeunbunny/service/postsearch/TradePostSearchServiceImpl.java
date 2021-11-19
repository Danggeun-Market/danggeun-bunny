package com.example.danggeunbunny.service.postsearch;

import com.example.danggeunbunny.annotation.area.AreaInfoRequired;
import com.example.danggeunbunny.dto.location.AddressRequestDto;
import com.example.danggeunbunny.dto.post.PostPageResponseDto;
import com.example.danggeunbunny.dto.post.PostResponseDto;
import com.example.danggeunbunny.model.address.Address;
import com.example.danggeunbunny.model.board.post.Post;
import com.example.danggeunbunny.model.user.User;
import com.example.danggeunbunny.repository.postsearch.PostSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradePostSearchServiceImpl implements PostSearchService{

    private final PostSearchRepository postSearchRepository;

    @Override
    @AreaInfoRequired
    public PostPageResponseDto findAllByUserAddress(User user, Pageable pageable) {
        Address address = user.getAddress();
        Page<Post> posts = postSearchRepository.findAllByUserAddress(address.getState(),address.getCity(), address.getTown(), pageable);

        return getPostPageResponse(posts,pageable);

    }


    @Override
    public PostPageResponseDto findAllByAddress(AddressRequestDto address, Pageable pageable) {

        Page<Post> posts = postSearchRepository.findAllByUserAddress(address.getState(), address.getCity(), address.getCity(), pageable);

        List<PostResponseDto> postResponseDtos = posts.getContent().stream().map(PostResponseDto::of).collect(Collectors.toList());

        return PostPageResponseDto.builder()
                .totalPage(posts.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .postResponses(postResponseDtos)
                .build();
    }

    @Override
    @AreaInfoRequired
    public PostPageResponseDto findALlByCategory(String category, User user, Pageable pageable) {

        Address address = user.getAddress();
        Page<Post> posts = postSearchRepository.findAllByUserAddress(category, address.getCity(), address.getTown(), address.getState(), pageable);

        return getPostPageResponse(posts, pageable);
    }

    private PostPageResponseDto getPostPageResponse(Page<Post> posts, Pageable pageable) {

        List<PostResponseDto> postResponseDtos = posts.getContent().stream().map(PostResponseDto::of).collect(Collectors.toList());

        return PostPageResponseDto.builder()
                .totalPage(posts.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .postResponses(postResponseDtos)
                .build();
    }


}
