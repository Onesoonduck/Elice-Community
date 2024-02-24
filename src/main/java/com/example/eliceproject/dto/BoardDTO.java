package com.example.eliceproject.dto;

import com.example.eliceproject.entity.Post;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BoardDTO {

    private Integer id;
    private String title;
    private String content;
    private String writer;
    private LocalDate created_at;
    private List<Post> posts;

}