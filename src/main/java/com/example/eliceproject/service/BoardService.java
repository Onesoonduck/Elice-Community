package com.example.eliceproject.service;

import com.example.eliceproject.entity.Board;
import com.example.eliceproject.exception.ServiceLogicException;
import com.example.eliceproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.eliceproject.exception.ExceptionCode;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    private Board foundBoard;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시판 리스트
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    // 게시판 검색
    public Board findBoardById(Integer id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        board.setCreatedAt(LocalDateTime.now());

        return board;
    }

    // 게시물 생성
    public Board boardWrite(Board board) {
        return boardRepository.create(board);
    }

    // 게시물 수정
    public Board boardUpdate(Board board) {
        foundBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        Optional.ofNullable(board.getTitle())
                .ifPresent(title -> { foundBoard = foundBoard.toBuilder().title(title).build(); });

        foundBoard = foundBoard.toBuilder()
                .content(board.getContent())
                .writer(board.getWriter())
                .build();

        return boardRepository.update(foundBoard);
    }

    // 게시물 삭제
    public void boardDelete(Integer boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            boardRepository.delete(boardId);
        } else {
            throw new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND);
        }
    }
}
