package com.example.eliceproject.mapper;

import com.example.eliceproject.dto.PostDTO;
import com.example.eliceproject.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    Post postDTOToPost(PostDTO postDTO);
}
