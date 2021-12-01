package com.example.danggeunbunny.service.postsearch;

import com.example.danggeunbunny.dto.location.AddressRequestDto;
import com.example.danggeunbunny.dto.post.PostPageResponseDto;
import com.example.danggeunbunny.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PostSearchService {

    public PostPageResponseDto findAllByUserAddress( User user, Pageable pageable);

    public PostPageResponseDto findAllByAddress(AddressRequestDto address, Pageable pageable);

    public PostPageResponseDto findALlByCategory(String category, User user, Pageable pageable);


}