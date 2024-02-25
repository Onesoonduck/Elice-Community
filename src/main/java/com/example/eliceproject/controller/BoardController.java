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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;

    @Autowired
    private BoardMapper boardMapper;


    // 메인화면
    @GetMapping("/main")
    public String main (Model model) {
        model.addAttribute("main", boardService.boardList());
        return "main";
    }

    // 게시판
    @GetMapping("/board")
    public String boardlist (Model model) {
        List<Board> boards = boardService.boardList();
        model.addAttribute("list", boards);
        return "board";
    }

    // 게시판 생성
    @GetMapping("/board/write")
    public String boardwriteform() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(@ModelAttribute BoardDTO boardDTO, Model model) {

        Board board = boardMapper.BoardDTOToBoard(boardDTO);
        boardService.boardWrite(board);
        model.addAttribute("message", "작성이 완료되었습니다.");

        return "redirect:/board";
    }

    // 게시판 키워드 검색
    @GetMapping("/board/{id}")
    public String getBoard(@PathVariable("id") Integer id,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(required = false) String keyword,
                           Model model) {
        Board board = boardService.findBoardById(id);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = postService.findPostsByBoardAndKeyword(board, keyword, pageRequest);

        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);
        return "board";
    }

    // 게시판 수정
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, @ModelAttribute BoardDTO boardDTO, Model model) {

        Board board = boardMapper.BoardDTOToBoard(boardDTO).toBuilder().id(id).build();
        boardService.boardUpdate(board);
        model.addAttribute("message", "수정이 완료되었습니다.");

        return "redirect:/board";
    }

    // 게시판 삭제
    @DeleteMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable("id") Integer id, Model model) {

        boardService.boardDelete(id);
        model.addAttribute("message", "삭제되었습니다.");

        return "redirect:/board";
    }

}
