package com.example.eliceproject.controller;

import com.example.eliceproject.entity.Post;
import com.example.eliceproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/write")
    public String postWriteForm() {
        return "postwrite";
    }

    @PostMapping("/post/writepro")
    public String boardWritePro(Post post) {

        postService.write(post);

        return "";
    }

    @GetMapping("/board")
    public String boardList() {
        return "boardlist";
    }
}
