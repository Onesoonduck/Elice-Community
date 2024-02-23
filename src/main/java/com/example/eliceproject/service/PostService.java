package com.example.eliceproject.service;

import com.example.eliceproject.entity.Post;
import com.example.eliceproject.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시글 작성
    public void postwrite(Post post) {
        postRepository.save(post);
    }

    // 게시판 리스트 처리
    public Page<Post> postList(Pageable pageable) {

        return postRepository.findAll(pageable);
    }

    public Page<Post> postSearchList(String searchKeyword, Pageable pageable) {

        return postRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 불러오기
    public Post postView(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("POST_NOT_FOUND"));
    }

    // 특정 게시글 삭제
    public void postDelete (Integer id) {
        postRepository.deleteById(id);
    }

}