package com.example.eliceproject.controller;

import com.example.eliceproject.dto.BoardDTO;
import com.example.eliceproject.entity.Board;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.mapper.BoardMapper;
import com.example.eliceproject.service.BoardService;
import com.example.eliceproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;

    @Autowired
    private BoardMapper boardMapper;

    public BoardController(BoardService boardService, PostService postService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.postService = postService;
        this.boardMapper = boardMapper;
    }

    // 게시판
    @GetMapping
    public String getBoards (Model model) {
        List<Board> boards = boardService.boardList();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    // 게시판 생성
    @GetMapping("/write")
    public String boardWriteForm(Model model) {
        return "board/boardwrite";
    }

    @PostMapping("/write")
    public String boardWrite(Board board) {
        boardService.boardWrite(board);

        return "redirect:/boards";
    }

    // 게시판 키워드 검색
    @GetMapping("/{boardId}")
    public String getBoard(@PathVariable Integer boardId,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(required = false) String keyword,
                           Model model) {
        Board board = boardService.findBoardById(boardId);

        if (board != null) {
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<Post> postPage = postService.findPostsByBoardAndKeyword(board, keyword, pageRequest);

            for (Post post : postPage.getContent()) {
                post.setCreatedAt(LocalDateTime.now());
            }

            model.addAttribute("board", board);
            model.addAttribute("keyword", keyword);
            model.addAttribute("postPage", postPage);
        }

        return "board/board";
    }

    // 게시판 수정
    @GetMapping("/modify/{boardId}")
    public String boardModify(@PathVariable Integer boardId, Model model) {
        Board board = boardService.findBoardById(boardId);
        model.addAttribute("board", board);
        return "board/boardmodify";
    }

    @PostMapping("/modify/{boardId}")
    public String postUpdate(@PathVariable Integer boardId, Board board) {
        board.setId(boardId);
        boardService.boardUpdate(board);

        return "redirect:/boards";
    }

    // 게시판 삭제
    @DeleteMapping("/delete/{boardId}")
    public String boardDelete(@PathVariable Integer boardId) {

        boardService.boardDelete(boardId);

        return "redirect:/boards";
    }

}
