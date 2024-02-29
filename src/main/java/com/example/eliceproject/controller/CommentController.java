package com.example.eliceproject.controller;

import com.example.eliceproject.dto.CommentDTO;
import com.example.eliceproject.dto.PostDTO;
import com.example.eliceproject.entity.Comment;
import com.example.eliceproject.mapper.CommentMapper;
import com.example.eliceproject.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    // 댓글 생성
    @PostMapping
    public String createComment (@ModelAttribute CommentDTO commentDTO, @RequestParam Integer postId, RedirectAttributes redirectAttributes) {
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        commentService.createComment(postId, comment);
        redirectAttributes.addAttribute("postId", postId);

        return "redirect:/posts/{postId}";
    }

    // 댓글 수정
    @PostMapping("/update/{commentId}")
    public String updateComment(@PathVariable Integer commentId, @ModelAttribute CommentDTO commentDTO, RedirectAttributes redirectAttributes) {

        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        Comment updatedComment = commentService.updateComment(commentId, comment);

        redirectAttributes.addAttribute("postId", updatedComment.getPost().getId());

        return "redirect:/posts/{postId}";
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);

        return "redirect:/posts";
    }
}
