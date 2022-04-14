package com.gurukulams.core.service;

import com.gurukulams.core.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {

    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(BoardService.class);

    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;

    /**
     * this is the constructor.
     * @param ajdbcTemplate a jdbcTemplate
     * @param adataSource a dataSource
     */
    public BoardService(final JdbcTemplate ajdbcTemplate,
                           final DataSource adataSource) {
        this.jdbcTemplate = ajdbcTemplate;
        this.dataSource = adataSource;
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return p
     * @throws SQLException
     */
    private Board rowMapper(final ResultSet rs,
                               final Integer rowNum)
            throws SQLException {
        Board board = new Board((long)
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getString("created_by"),
                rs.getObject("modified_at", LocalDateTime.class),
                rs.getString("modified_by"));

        return board;
    }

    /**
     * creates new syllabus.
     * @param userName the userName
     * @param board the syllabus
     * @return board optional
     */
    public Board create(final String userName,
                           final Board board) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("boards").usingGeneratedKeyColumns("id")
                .usingColumns("title", "description", "created_by");

        final Map<String, Object> valueMap = new HashMap<>();

        valueMap.put("title", board.title());
        valueMap.put("description", board.description());
        valueMap.put("created_by", userName);

        final Number boardId = insert.executeAndReturnKey(valueMap);
        final Optional<Board> createdBoard =
                read(userName, boardId.longValue());

        logger.info("Syllabus Created {}", boardId);

        return createdBoard.get();
    }

    /**
     * reads from syllabus.
     * @param id the id
     * @param userName the userName
     * @return board optional
     */
    public Optional<Board> read(final String userName, final Long id) {
        final String query = "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM boards "
                + "WHERE id = ?";


        try {
            final Board p = jdbcTemplate
                    .queryForObject(query, new Object[]{id},
                            this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update the board.
     * @param id the id
     * @param userName the userName
     * @param board the board
     * @return board optional
     */
    public Board update(final Long id,
                           final String userName,
                           final Board board) {
        logger.debug("Entering update for Board {}", id);
        final String query = "UPDATE boards SET title=?,"
                + "description=?,modified_by=? WHERE id=?";
        final Integer updatedRows =
                jdbcTemplate.update(query, board.title(),
                        board.description(), userName, id);
        if (updatedRows == 0) {
            logger.error("Update not found", id);
            throw new IllegalArgumentException("Board not found");
        }
        return read(userName, id).get();
    }

    /**
     * delete the board.
     * @param id the id
     * @param userName the userName
     * @return board optional
     */
    public Boolean delete(final String userName, final Long id) {
        final String query = "DELETE FROM boards WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }

    /**
     * list the board.
     * @param userName the userName
     * @return board optional
     */
    public List<Board> list(final String userName) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at,modified_at,modified_by FROM boards";
        return jdbcTemplate.query(query, this::rowMapper);

    }

    /**
     * Cleaning up all boards.
     *
     */
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM boards_grades_syllabus");
        jdbcTemplate.update("DELETE FROM boards_grades");
        jdbcTemplate.update("DELETE FROM boards");
    }
}
