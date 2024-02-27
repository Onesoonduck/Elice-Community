package com.example.eliceproject.service;

import com.example.eliceproject.entity.Board;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.exception.ExceptionCode;
import com.example.eliceproject.exception.ServiceLogicException;
import com.example.eliceproject.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 게시글 리스트 처리
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

        return postRepository.save(foundPost);
    }

    // 특정 게시글 불러오기
    public Post postView(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("POST_NOT_FOUND"));
    }

    // 게시판 키워드 검색
    public Page<Post> findPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest) {
        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findAllByBoardAndTitleContaining(board, keyword, pageRequest);
        } else {
            return postRepository.findAllByBoardOrderByCreatedAtDesc(board, pageRequest);
        }
    }

    // 특정 게시글 삭제
    public void postDelete (Integer id) {
        postRepository.deleteById(id);
    }

    // 게시판에 속한 게시글을 가져오는 메서드
    public List<Post> findPostByBoardId(Integer boardId) {
        return postRepository.findByBoardId(boardId);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

}