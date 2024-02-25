package com.example.eliceproject.repository;

import com.example.eliceproject.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post>findByTitleContaining(String searchKeyword, Pageable pageable);

}
