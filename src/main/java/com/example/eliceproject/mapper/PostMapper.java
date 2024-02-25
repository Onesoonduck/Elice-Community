package com.example.eliceproject.mapper;

import com.example.eliceproject.dto.PostDTO;
import com.example.eliceproject.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postDTOToPost(PostDTO postDTO);

};
