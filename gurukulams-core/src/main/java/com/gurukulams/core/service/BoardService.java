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
import java.util.Locale;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;


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
     * @param locale the locale
     * @return board optional
     */
    public Board create(final String userName,
                        final Locale locale,
                           final Board board) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("boards").usingGeneratedKeyColumns("id")
                .usingColumns("title", "description", "created_by");

        final Map<String, Object> valueMap = new HashMap<>();

        valueMap.put("title", board.title());
        valueMap.put("description", board.description());
        valueMap.put("created_by", userName);

        final Number boardId = insert.executeAndReturnKey(valueMap);

        if (locale != null) {
            valueMap.put("board_id", boardId);
            valueMap.put("locale", locale.getLanguage());
            createLocalizedBoard(valueMap);
        }

        final Optional<Board> createdBoard =
                read(userName, locale, boardId.longValue());

        logger.info("Syllabus Created {}", boardId);

        return createdBoard.get();
    }

    /**
     * Create Localized Board.
     * @param valueMap
     * @return noOfBoards
     */
    private int createLocalizedBoard(final Map<String, Object> valueMap) {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("boards_localized")
                .usingColumns("board_id", "locale", "title", "description")
                .execute(valueMap);
    }

    /**
     * reads from syllabus.
     * @param id the id
     * @param locale the locale
     * @param userName the userName
     * @return board optional
     */
    public Optional<Board> read(final String userName,
                                final Locale locale, final Long id) {

        final String query = locale == null
              ? "SELECT id,title,description,created_by,"
              + "created_at, modified_at, modified_by FROM boards "
              + "WHERE id = ?"
              : "SELECT boards.id,boards_localized.title,"
              + "boards_localized.description, boards.created_by,"
              + "boards.created_at, boards.modified_at,"
              + "boards.modified_by FROM boards "
              + "JOIN boards_localized ON boards.id=boards_localized.board_id "
              + "WHERE boards_localized.board_id = ?"
              + "AND boards_localized.locale = ?";

        try {
            final Board p = locale == null ? jdbcTemplate
                    .queryForObject(query, new Object[]{id},
                            this::rowMapper)
                    : jdbcTemplate
                            .queryForObject(query, new Object[]{id,
                                            locale.getLanguage()},
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
     * @param locale the locale
     * @return board optional
     */
    public Board update(final Long id,
                           final String userName,
                           final Locale locale,
                           final Board board) {
        logger.debug("Entering update for Board {}", id);
        final String query = locale == null
                ? "UPDATE boards SET title=?,"
                + "description=?,modified_by=? WHERE id=?"
                : "UPDATE boards SET modified_by=? WHERE id=?";
        Integer updatedRows = locale == null
                ? jdbcTemplate.update(query, board.title(),
                        board.description(), userName, id)
                : jdbcTemplate.update(query, userName, id);
        if (updatedRows == 0) {
            logger.error("Update not found", id);
            throw new IllegalArgumentException("Board not found");
        } else if (locale != null) {
            updatedRows = jdbcTemplate.update(
                    "UPDATE boards_localized SET title=?,locale=?,"
                    + "description=? WHERE board_id=? AND locale=?",
                    board.title(), locale.getLanguage(),
                    board.description(), id, locale.getLanguage());
            if (updatedRows == 0) {
                final Map<String, Object> valueMap = new HashMap<>(4);
                valueMap.put("board_id", id);
                valueMap.put("locale", locale.getLanguage());
                valueMap.put("title", board.title());
                valueMap.put("description", board.description());
                createLocalizedBoard(valueMap);
            }
        }
        return read(userName, locale, id).get();
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
        jdbcTemplate.update("DELETE FROM boards_grades_subjects");
        jdbcTemplate.update("DELETE FROM boards_grades");
        jdbcTemplate.update("DELETE FROM boards_localized");
        jdbcTemplate.update("DELETE FROM boards");
    }
}
