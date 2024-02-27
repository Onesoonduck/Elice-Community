package com.example.eliceproject.repository;

import com.example.eliceproject.entity.Board;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
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
            return Board.builder()
                    .id(resultSet.getInt("id"))
                    .title(resultSet.getString("title"))
                    .content(resultSet.getString("content"))
                    .writer(resultSet.getString("writer"))
                    .createdAt(resultSet.getTimestamp("createdAt").toLocalDateTime())
                    .build();
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

            return Optional.ofNullable(board);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Board create(Board board) {
        String insertSql = "INSERT INTO Board (title, content, writer, createdAt) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime createdAt = LocalDateTime.now();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"id"});
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriter());
            ps.setTimestamp(4, Timestamp.valueOf(createdAt));

            return ps;

        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) return board;
        return board.toBuilder()
                .id(key.intValue())
                .createdAt(createdAt)
                .build();
    }


    public Board update(Board board) {
        String updateSql = "UPDATE Board SET title = ?, content = ?, writer = ? WHERE id = ?";

        if (board.getContent() == null) {
            board.setContent("");
        }
        if (board.getWriter() == null) {
            board.setWriter("");
        }
        if (board.getTitle() == null) {
            board.setTitle("");
        }

        jdbcTemplate.update(updateSql, board.getTitle(), board.getContent(), board.getWriter(), board.getId());

        return board;
    }
    public void delete(Integer boardId) {
        String sql = "DELETE FROM Board WHERE id = ?";
        jdbcTemplate.update(sql, boardId);
    }


}
