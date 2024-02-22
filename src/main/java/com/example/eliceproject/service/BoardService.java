package com.example.eliceproject.service;

import com.example.eliceproject.entity.Board;
import com.example.eliceproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eliceproject.exception.ServiceLogicException;
import com.example.eliceproject.exception.ExceptionCode;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    // 게시판 검색
    public Board findBoardById(Integer id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    // 게시물 생성
    public Board createBoard(Board board) {
        return boardRepository.create(board);
    }

    // 게시물 수정
    public Board updateBoard(Board board) {
        Board foundBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        return boardRepository.update(foundBoard);
    }

    // 게시물 삭제
    public void delete(Integer id) {
        Board foundBoard = boardRepository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        boardRepository.delete(foundBoard);
    }

}
