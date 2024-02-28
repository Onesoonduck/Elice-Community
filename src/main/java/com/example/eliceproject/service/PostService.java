package com.example.eliceproject.service;

import com.example.eliceproject.entity.Board;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.exception.ExceptionCode;
import com.example.eliceproject.exception.ServiceLogicException;
import com.example.eliceproject.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BoardService boardService;

    // 게시글 작성
    public Post postwrite(Post post, Integer boardId) {

        Board boardToCreate = boardService.findBoardById(boardId);
        if (boardToCreate != null) {
            post.setBoard(boardToCreate);
            return postRepository.save(post);
        } else {
            try {
                throw new IllegalAccessException("Board not found for boardId: " + boardId);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 게시글 리스트 처리
    public Page<Post> postList(Pageable pageable) {

        return postRepository.findAll(pageable);
    }

    // 게시글 수정
    public Post updatePost(Post post, Integer postId) {
        Post foundPost = postRepository.findById(postId)
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
    public Post postView(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("POST_NOT_FOUND"));

        post.setViewcount(post.getViewcount() + 1);
        postRepository.save(post);

        return post;
    }

    // 게시판 키워드 검색
    public Page<Post> findPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest) {
        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findAllByBoardAndTitleContaining(board, keyword, pageRequest);
        } else {
            return postRepository.findAllByBoardOrderByCreatedAtDesc(board, pageRequest);
        }
    }

    // 게시물 찾기
    public Post findPost(Integer postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));
    }

    // 특정 게시글 삭제
    @Transactional
    public void postDelete (Integer postId) {
        Post foundPost = postRepository.findById(postId)
                        .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));

        postRepository.deleteById(postId);
    }

}