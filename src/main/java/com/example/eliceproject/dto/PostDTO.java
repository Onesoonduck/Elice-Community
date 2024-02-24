package com.example.eliceproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PostDTO {
    private String title;
    private String content;
    private String writer;
    private int viewcount;
    private LocalDate createdAt;
}
