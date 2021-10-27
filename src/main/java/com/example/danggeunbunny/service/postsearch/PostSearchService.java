package com.example.danggeunbunny.service.postsearch;

import com.example.danggeunbunny.dto.post.PostPageResponseDto;
import com.example.danggeunbunny.dto.post.PostResponseDto;
import com.example.danggeunbunny.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface PostSearchService {

    public PostPageResponseDto findAllUserAddress(User user, Pageable pageable);

}
