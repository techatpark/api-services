package com.gurukulams.core.service;

import com.gurukulams.core.model.Handle;
import com.gurukulams.core.model.Institute;
import com.gurukulams.core.model.LearnerProfile;
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
     *
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


        Institute institute = new Institute(
                rs.getString("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getString("created_by"),
                rs.getObject("modified_at", LocalDateTime.class),
                rs.getString("modified_by"));

        return institute;
    }

    private Handle rowMapperHandle(final ResultSet resultSet,
                                   final int i)
            throws SQLException {


        Handle handle = new Handle(resultSet.getString("id"),
                resultSet.getString("type"),
                resultSet.getObject("created_at", LocalDateTime.class)
        );
        return handle;
    }

    /**
     * inserts data.
     *
     * @param userName  the userName
     * @param institute the institute
     * @return question optional
     */
    public Institute create(final String userName,
                            final Institute institute) {

        Optional<Handle> handle = createHandle(userName,
                institute, "Institute");

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("institutes")
                .usingColumns("id", "title",
                        "description",
                        "created_by");

        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("id", institute.id());
        valueMap.put("title",
                institute.title());
        valueMap.put("description", institute.description());
        valueMap.put("created_by", userName);

//        final UUID instituteId = UUID.randomUUID();
        insert.execute(valueMap);
        final Optional<Institute> createdInstitute =
                read(userName, institute.id());

        logger.info("Created Institute {}", institute.id());

        return createdInstitute.get();
    }

    private Optional<Handle> createHandle(final String userName,
                                          final Institute institute,
                                          final String type) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("handle")
                .usingColumns("id", "type");
        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("id", institute.id());
        valueMap.put("type", type);

        insert.execute(valueMap);

        final Optional<Handle> createdHandle = readHandle(userName,
                institute.id());
        return createdHandle;
    }

    private Optional<Handle> readHandle(final String userName,
                                        final String id) {
        final String query = "SELECT id,type,created_at"
                + " FROM handle WHERE id = ?";

        try {
            final Handle p = jdbcTemplate.queryForObject(query,
                    this::rowMapperHandle, id);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    /**
     * reads from institute.
     *
     * @param id       the id
     * @param userName the userName
     * @return question optional
     */
    public Optional<Institute> read(final String userName, final String id) {
        final String query = "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM institutes "
                + "WHERE id = ?";


        try {
            final Institute p = jdbcTemplate
                    .queryForObject(query, this::rowMapper, id);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update the institute.
     *
     * @param id        the id
     * @param userName  the userName
     * @param institute the institute
     * @return question optional
     */
    public Institute update(final String id,
                            final String userName,
                            final Institute institute) {
        logger.debug("Entering Update for Institute {}", id);
        final String query =
                "UPDATE institutes SET title = ?,"
                        + "description = ?, modified_by = ? WHERE id = ?";
        final Integer updatedRows =
                jdbcTemplate.update(query, institute.title(),
                        institute.description(), userName, id);
        if (updatedRows == 0) {
            logger.error("Update not found {}", id);
            throw new IllegalArgumentException("Institute not found");
        }
        return read(userName, id).get();
    }

    /**
     * delete the institute.
     *
     * @param id       the id
     * @param userName the userName
     * @return false
     */
    public Boolean delete(final String userName, final String id) {
        String query = "DELETE FROM institutes WHERE ID=?";

        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }


    /**
     * list of institutes.
     *
     * @param userName the userName
     * @return institutes list
     */
    public List<Institute> list(final String userName) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM institutes";
        return jdbcTemplate.query(query, this::rowMapper);
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
