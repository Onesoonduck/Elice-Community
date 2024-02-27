package com.example.eliceproject.dto;

import com.example.eliceproject.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PostDTO {

    private Integer id;
    private String title;
    private String content;
    private String writer;
    private Integer viewcount;
    private LocalDateTime createdAt;
    private Board board;
}
