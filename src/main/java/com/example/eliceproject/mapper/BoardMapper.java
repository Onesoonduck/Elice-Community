package com.example.eliceproject.mapper;

import com.example.eliceproject.dto.BoardDTO;
import com.example.eliceproject.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    Board boardDTOToBoard(BoardDTO boardDTO);
}
