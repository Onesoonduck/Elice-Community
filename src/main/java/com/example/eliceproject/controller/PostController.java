package com.example.eliceproject.controller;

import com.example.eliceproject.dto.PostDTO;
import com.example.eliceproject.entity.Board;
import com.example.eliceproject.entity.Comment;
import com.example.eliceproject.exception.ExceptionCode;
import com.example.eliceproject.exception.ServiceLogicException;
import com.example.eliceproject.mapper.PostMapper;
import com.example.eliceproject.service.BoardService;
import com.example.eliceproject.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CommentService commentService;

    // 게시글
    @GetMapping("/{postId}")
    public String postList(@PathVariable Integer postId, Model model) {

        Post post = postService.findPost(postId);
        model.addAttribute("post", post);

        List<Comment> comments = commentService.findCommentByPostId(postId);
        model.addAttribute("comments", comments);

        return "post/postview";
    }

    // 게시글 작성
    @GetMapping("/write")
    public String postWrite(@RequestParam Integer boardId, Model model) {

        model.addAttribute("boardId", boardId);

        return "post/postwrite";
    }

    // 게시글 작성
    @PostMapping("/writepro")
    public String postWriteForm(@ModelAttribute PostDTO postDTO, @RequestParam Integer boardId, Model model) {

        try {
            Board board = boardService.findBoardById(boardId);
            if (board == null) {
                throw new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND);
            }
            Post post = postMapper.postDTOToPost(postDTO);
            post.setBoard(board);
            Post writedPost = postService.postwrite(post, boardId);

            model.addAttribute("message", "작성이 완료되었습니다.");

            return "redirect:/boards/" + writedPost.getBoard().getId();

        } catch (ServiceLogicException ex) {
            model.addAttribute("error", ex.getMessage());
            return "error-page";
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public String postDelete(@PathVariable Integer postId, RedirectAttributes redirectAttributes) {

        postService.postDelete(postId);
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");

        return "redirect:/posts";
    }

    // 게시글 수정
    @GetMapping("/update/{postId}")
    public String postUpdateFrom(@PathVariable Integer postId, Model model) {

        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "post/postmodify";
    }

    @PostMapping("/update/{postId}")
    public String postUpdate(@PathVariable Integer postId, @ModelAttribute PostDTO postDTO, RedirectAttributes redirectAttributes) {

        Post post = postMapper.postDTOToPost(postDTO);
        Post updatedPost = postService.updatePost(post, postId);

        redirectAttributes.addAttribute("postId", updatedPost.getId());
        redirectAttributes.addFlashAttribute("message", "수정이 완료되었습니다.");

        return "redirect:/posts/{postId}";
    }

}