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


    private final BoardRepository boardRepository;

    private Board foundBoard;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    // 해당 게시판 보기
    public Board boardview(Integer id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    // 게시판 검색
    public Board findBoardById(Integer id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    // 게시물 생성
    public Board boardwrite(Board board) {
        return boardRepository.create(board);
    }

    // 게시물 수정
    public Board boardupdate(Board board) {
        Board foundBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        foundBoard.setTitle(board.getTitle());
        foundBoard.setContent(board.getContent());
        foundBoard.setWriter(board.getWriter());

        return boardRepository.update(foundBoard);
    }

    // 게시물 삭제
    public void boarddelete(Integer id) {
        Board foundBoard = boardRepository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        boardRepository.delete(foundBoard);
    }

}
