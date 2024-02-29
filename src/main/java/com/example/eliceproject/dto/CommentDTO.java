package com.example.eliceproject.dto;

import com.example.eliceproject.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentDTO {

    private Integer id;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private Post post;

}
