package com.example.eliceproject.mapper;

import com.example.eliceproject.dto.CommentDTO;
import com.example.eliceproject.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "content", source = "content")
    Comment commentDTOToComment(CommentDTO commentDTO);
}
