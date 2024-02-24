package com.example.eliceproject.mapper;

import com.example.eliceproject.dto.BoardDTO;
import com.example.eliceproject.entity.Board;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board BoardDTOToBoard(BoardDTO boardDTO);
}
