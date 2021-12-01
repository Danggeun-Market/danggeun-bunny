package com.example.danggeunbunny.domain.postsearch.service;

import com.example.global.dto.location.AddressRequestDto;
import com.example.danggeunbunny.domain.Post.presentation.dto.response.PostPageResponse;
import com.example.danggeunbunny.domain.user.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PostSearchService {

     PostPageResponse findAllByUserAddress(User user, Pageable pageable);

     PostPageResponse findAllByAddress(AddressRequestDto address, Pageable pageable);

     PostPageResponse findALlByCategory(String category, User user, Pageable pageable);


}