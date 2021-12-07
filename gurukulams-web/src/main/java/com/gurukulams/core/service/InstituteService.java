package com.gurukulams.core.service;

import com.gurukulams.core.model.Institute;
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

/**
 * The type Institute service.
 */
@Service
public final class InstituteService {

    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(InstituteService.class);


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
     * @param anJdbcTemplate
     * @param aDataSource
     */
    public InstituteService(
            final JdbcTemplate anJdbcTemplate, final DataSource aDataSource) {
        this.jdbcTemplate = anJdbcTemplate;
        this.dataSource = aDataSource;
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return p
     * @throws SQLException
     */
    private Institute rowMapper(final ResultSet rs,
                                             final Integer rowNum)
            throws SQLException {


        Institute institute = new Institute((long) rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getString("created_by"),
                rs.getObject("modified_at", LocalDateTime.class),
                rs.getString("modified_by"));

        return institute;
    }
    /**
     * inserts data.
     * @param userName the userName
     * @param institute the institute
     * @param boardId the board Id
     * @return question optional
     */
    public Institute create(final Long boardId, final String userName,
                                      final Institute institute) {

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("institutes")
                .usingGeneratedKeyColumns("id")
                .usingColumns("board_id", "title",
                        "description",
                        "created_by");

        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("board_id", boardId);
        valueMap.put("title",
                institute.title());
        valueMap.put("description", institute.description());
        valueMap.put("created_by", userName);

        final Number instituteId = insert.executeAndReturnKey(valueMap);
        final Optional<Institute> createdInstitute =
                read(boardId, userName, instituteId.longValue());

        logger.info("Created Institute {}", instituteId);

        return createdInstitute.get();
    }


    /**
     * reads from institute.
     * @param id the id
     * @param userName the userName
     * @param boardId the board Id
     * @return institute optional
     */
    public Optional<Institute> read(final Long boardId,
                                    final String userName, final Long id) {
        final String query = "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM institutes "
                + "WHERE id = ? and board_id = ?";


        try {
            final Institute p = jdbcTemplate
                    .queryForObject(query, new Object[]{id, boardId},
                            this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update the institute.
     * @param id the id
     * @param userName the userName
     * @param institute the institute
     * @param boardId the board Id
     * @return question optional
     */
    public Institute update(final Long boardId, final Long id,
                                     final String userName,
                                final Institute institute) {
        logger.debug("Entering Update for Institute {}", id);
        final String query = "UPDATE institutes SET title=?,"
                + "description=?,modified_by=? WHERE id=? and board_id = "
                + "?";
        final Integer updatedRows =
                jdbcTemplate.update(query, institute.title(),
                        institute.description(), userName, id, boardId);
        if (updatedRows == 0) {
            logger.error("Update not found {}", id);
            throw new IllegalArgumentException("Institute not found");
        }
        return read(boardId, userName, id).get();
    }

    /**
     * delete the institute.
     * @param id the id
     * @param userName the userName
     * @param boardId the board Id
     * @return false
     */
    public Boolean delete(final Long boardId, final String userName,
                                                         final Long id) {
        String query = "DELETE FROM institutes WHERE ID=? and board_id ="
                + " ?";

        final Integer updatedRows = jdbcTemplate.update(query, id, boardId);
        return !(updatedRows == 0);
    }


    /**
     * list of institutes.
     * @param userName the userName
     * @param boardId the board Id
     * @return institutes list
     */
    public List<Institute> list(final Long boardId, final String userName) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM institutes WHERE "
                + "board_id = ?";
        return jdbcTemplate.query(query, this::rowMapper, boardId);
    }

    /**
     * Cleaning up all institutes.
     *
     * @return no.of institutes deleted
     */
    public Integer deleteAll() {
        final String query = "DELETE FROM institutes";
        return jdbcTemplate.update(query);
    }
}
