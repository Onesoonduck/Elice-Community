package com.example.eliceproject.repository;

import com.example.eliceproject.entity.Board;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository{

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Board> boardRowMapper() {
        return (resultSet, rowNum) -> {
            Board board = new Board();
            if (resultSet != null) {
                board.setTitle(resultSet.getString("title"));
                board.setContent(resultSet.getString("content"));
                board.setWriter(resultSet.getString("writer"));
                board.setCreatedAt(resultSet.getTimestamp("createdAt").toLocalDateTime());
            }
            return board;
        };
    }

    public List<Board> findAll() {
        String sql = "SELECT * FROM Board ORDER BY createdAt DESC";
        return jdbcTemplate.query(sql, boardRowMapper());
    }

    public Optional<Board> findById(Integer id) {

        try {
            String sql = "SELECT * FROM Board WHERE id = ?";
            Board board = jdbcTemplate.queryForObject(sql, boardRowMapper(), id);
            return Optional.of(board);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Board create(Board board) {
        String insertSql = "INSERT INTO Board (title, content, writer, createdAt) VALUES (?, ?, ?, ?)";
        LocalDateTime createdAt = LocalDateTime.now();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriter());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            return ps;
        }, keyHolder);

        // 생성된 키 가져오기
        Number key = keyHolder.getKey();

        if (key != null) {
            board.setId(key.intValue());
            board.setCreatedAt(createdAt);
        }

        return board;
    }

    public Board update(Board board) {
        String updateSql = "UPDATE Board SET title = ?, content = ?, writer = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, board.getTitle(), board.getContent(), board.getWriter(), board.getId());

        return board;
    }

    public void delete(Board board) {
        String sql = "DELETE FROM Board WHERE id = ?";
        jdbcTemplate.update(sql, board.getId());
    }

}
