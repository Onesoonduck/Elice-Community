package com.example.eliceproject.controller;

import com.example.eliceproject.entity.Board;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.service.BoardService;
import com.example.eliceproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;

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
    public String postUpdate(@PathVariable("id") Integer id, Board board, Model model) {

        Board boardTemp = boardService.boardview(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.boardwrite(boardTemp);

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board");

        return "message";
    }

    // 게시판 삭제
    @GetMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable("id") Integer id, Model model) {

        boardService.boarddelete(id);

        model.addAttribute("message", "삭제되었습니다.");
        model.addAttribute("searchUrl", "/board");

        return "message";
    }

}
