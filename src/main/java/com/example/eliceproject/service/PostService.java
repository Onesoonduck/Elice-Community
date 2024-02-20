package com.example.eliceproject.service;

import com.example.eliceproject.entity.Post;
import com.example.eliceproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시글 작성
    public void postwrite(Post post) {
        postRepository.save(post);
    }

    // 게시판 리스트 처리
    public List<Post> boardList() {
        return postRepository.findAll();
    }

    // 특정 게시글 불러오기
    public Post boardView(Integer id) {
        return postRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void postDelete (Integer id) {
        postRepository.deleteById(id);
    }
}
