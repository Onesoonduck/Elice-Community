package com.example.eliceproject.controller;

import com.example.eliceproject.dto.BoardDTO;
import com.example.eliceproject.entity.Board;
import com.example.eliceproject.mapper.BoardMapper;
import com.example.eliceproject.service.BoardService;
import com.example.eliceproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;

    @Autowired
    private BoardMapper boardMapper;

    public BoardController (BoardService boardService, PostService postService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.postService = postService;
        this.boardMapper = boardMapper;
    }

    // 메인화면
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("main", boardService.boardList());
        return "main";
    }

    // 게시판
    @GetMapping("/board")
    public String boardlist(Model model) {
        model.addAttribute("list", boardService.boardList());
        return "board";
    }

    // 게시판 생성
    @GetMapping("/board/write")
    public String boardwriteform() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model) {

        boardService.boardwrite(board);

        model.addAttribute("message", "작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board");

        return "message";
    }

    // 선택한 게시판 보기
    @GetMapping("/board/{id}")
    public String getOneBoard(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("boardview", boardService.boardview(id));
        return "postlist";
    }

    // 게시판 수정
    @GetMapping("board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("board", boardService.boardview(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, @ModelAttribute BoardDTO boardDTO, Model model) {

        Board board = boardMapper.BoardDTOToBoard(boardDTO).toBuilder().id(id).build();

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board");

        return "message";
    }

    // 게시판 삭제
    @DeleteMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable("id") Integer id, Model model) {

        boardService.boarddelete(id);

        model.addAttribute("message", "삭제되었습니다.");
        model.addAttribute("searchUrl", "/board");

        return "message";
    }

}
