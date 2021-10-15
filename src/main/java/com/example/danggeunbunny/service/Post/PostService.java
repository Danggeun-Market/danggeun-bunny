package com.example.danggeunbunny.service.Post;

import com.example.danggeunbunny.dto.post.PostCreateRequestDto;
import com.example.danggeunbunny.model.user.User;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    public void createNewPost(PostCreateRequestDto postCreateRequestDto, User user);

}
