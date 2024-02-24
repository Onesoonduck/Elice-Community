package com.example.eliceproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(Post post, String content, String writer, LocalDate created_at) {
        this.post = post;
        this.content = content;
        this.writer = writer;
        this.created_at = LocalDate.now();
    }

}
