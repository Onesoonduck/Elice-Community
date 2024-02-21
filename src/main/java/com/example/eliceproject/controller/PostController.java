package com.example.eliceproject.controller;

import org.springframework.ui.Model;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PostController {

    @Autowired
    private PostService postService;

    // 게시글 게시판
    @GetMapping("/post")
    public String postList(Model model) {
        model.addAttribute("list", postService.postList());
        return "postlist";
    }

    // 게시글 작성
    @GetMapping("/post/write")
    public String postWriteForm() {
        return "postwrite";
    }

    // 게시글 작성 후
    @PostMapping("/post/writepro")
    public String postWritePro(Post post, Model model) {

        postService.postwrite(post);

        model.addAttribute("message", "작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/post");

        return "redirect:/post";
    }

    // 선택한 게시물 보기
    @GetMapping("/post/view")
    public String postView(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("post", postService.postView(id));
        return "postview";
    }

    // 게시글 삭제
    @DeleteMapping("/post/delete")
    public String postDelete(@PathVariable("id") Integer id, Model model) {

        postService.postDelete(id);

        model.addAttribute("message", "삭제되었습니다.");
        model.addAttribute("searchUrl", "/post");

        return "redirect:/post";
    }

    // 게시글 수정하는 화면
    @GetMapping("post/modify/{id}")
    public String postModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("post", postService.postView(id));
        return "postmodify";
    }

    // 게시글 수정된 화면
    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, Post post, Model model) {

        Post postTemp = postService.postView(id);
        postTemp.setTitle(post.getTitle());
        postTemp.setContent(post.getContent());

        postService.postwrite(postTemp);

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/post");

        return "redirect:/post";
    }
}