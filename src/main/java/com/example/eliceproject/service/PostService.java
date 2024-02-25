package com.example.eliceproject.service;

import com.example.eliceproject.entity.Board;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.exception.ExceptionCode;
import com.example.eliceproject.exception.ServiceLogicException;
import com.example.eliceproject.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BoardService boardService;

    // 게시글 작성
    public Post postwrite(Post post, Integer id) {
        Board boardToCreate = boardService.findBoardById(id);
        post.setBoard(boardToCreate);
        return postRepository.save(post);
    }

    // 게시판 리스트 처리
    public Page<Post> postList(Pageable pageable) {

        return postRepository.findAll(pageable);
    }

    public Page<Post> postSearchList(String searchKeyword, Pageable pageable) {

        return postRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 게시글 수정
    public Post updatePost(Post post, Integer id) {
        post.setId(id);
        Post foundPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));

        Optional.ofNullable(post.getTitle())
                .ifPresent(title -> foundPost.setTitle(title));
        Optional.ofNullable(post.getContent())
                .ifPresent(content -> foundPost.setContent(content));
        Optional.ofNullable(post.getWriter())
                .ifPresent(writer -> foundPost.setWriter(writer));

        return postRepository.save(foundPost);
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