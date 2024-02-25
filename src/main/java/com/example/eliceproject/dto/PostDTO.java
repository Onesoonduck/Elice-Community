package com.example.eliceproject.dto;

import com.example.eliceproject.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PostDTO {

    private Integer id;
    private String title;
    private String content;
    private String writer;
    private int viewcount;
    private LocalDate createdAt;
    private Board board;
}
