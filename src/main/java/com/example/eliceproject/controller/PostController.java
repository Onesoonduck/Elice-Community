package com.example.eliceproject.controller;

import com.example.eliceproject.dto.PostDTO;
import com.example.eliceproject.mapper.PostMapper;
import com.example.eliceproject.service.BoardService;
import com.example.eliceproject.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CommentService commentService;

    // 게시글 게시판
    @GetMapping("/post")
    public String postList(Model model,
                           @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword) {

        Page<Post> list = null;
        if (searchKeyword == null) {
            list = postService.postList(pageable);
        } else {
            list = postService.postSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "postlist";
    }

    // 게시글 작성
    @GetMapping("/post/write")
    public String postWriteForm() {
        return "postwrite";
    }

    // 게시글 작성 후
    @PostMapping("/post/writepro")
    public String postWritePro(@ModelAttribute PostDTO postDTO, @RequestParam Integer id, Model model) {

       Post post = postMapper.postDTOToPost(postDTO);
       Post createdPost = postService.postwrite(post, id);

        model.addAttribute("message", "작성이 완료되었습니다.");

        return "redirect:/post/" + createdPost.getBoard().getId();
    }

    // 선택한 게시물 보기
    @GetMapping("/post/view/{id}")
    public String postView(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("post", postService.postView(id));
        return "postview";
    }

    // 게시글 삭제
    @DeleteMapping("/post/delete/{id}")
    public String postDelete(@PathVariable("id") Integer id, Model model) {

        postService.postDelete(id);

        model.addAttribute("message", "삭제되었습니다.");

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
    public String postUpdate(Post post, @PathVariable("id") Integer id, Model model) {
        Post postTemp = postService.postView(id);
        postTemp.setTitle(post.getTitle());
        postTemp.setContent(post.getContent());

        postService.postwrite(postTemp, id);

        model.addAttribute("message", "수정이 완료되었습니다.");
        return "redirect:/post";
    }

}