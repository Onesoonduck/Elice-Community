package com.example.eliceproject.mapper;

import com.example.eliceproject.dto.CommentDTO;
import com.example.eliceproject.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentDTOToComment(CommentDTO commentDTO);
}
