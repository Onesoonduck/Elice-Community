package com.example.eliceproject.service;

import com.example.eliceproject.entity.Post;
import com.example.eliceproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void write(Post post) {
        postRepository.save(post);
    }
}
