package com.gurukulams.core.service;

import com.gurukulams.core.model.Learner;
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
 * The type Learner service.
 */
@Service
public class LearnerService {

    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(LearnerService.class);

    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;
    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is the constructor.
     * @param anJdbcTemplate
     * @param anDataSource
     */
    public LearnerService(final DataSource anDataSource,
                          final JdbcTemplate anJdbcTemplate) {
        this.dataSource = anDataSource;
        this.jdbcTemplate = anJdbcTemplate;
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return p
     * @throws SQLException
     */
    private Learner rowMapper(final ResultSet rs,
                              final Integer rowNum)
            throws SQLException {

            Learner learner = new Learner((long)
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getObject("created_at", LocalDateTime.class),
                    rs.getString("created_by"),
                    rs.getObject("modified_at", LocalDateTime.class),
                    rs.getString("modified_by")
                );
             return learner;
            }

    /**
     *
     * @param userName
     * @param learner
     * @return learner
     */
    public Learner create(final String userName,
                          final Learner learner) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                                        .withTableName("learner")
                                        .usingGeneratedKeyColumns("id")
                          .usingColumns("title", "description", "created_by");
        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("title", learner.title());
        valueMap.put("description", learner.description());
        valueMap.put("created_by", userName);
        final Number learnerId = insert.executeAndReturnKey(valueMap);
        final Optional<Learner> createdLearner = read(userName,
                learnerId.longValue());
        logger.info("Created learner {}", learnerId);
        return createdLearner.get();
    }

    /**
     *
     * @param userName
     * @param id
     * @return learner
     */
    public Optional<Learner> read(final String userName,
                                 final Long id) {
            final String query = "SELECT id,title,description,created_by,"
                    + "created_at, modified_at, modified_by FROM learner "
                    + "WHERE id = ?";

        try {
            final Learner p = jdbcTemplate.queryForObject(query,
                    new Object[]{id}, this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     *
     * @param id
     * @param userName
     * @param learner
     * @return learner
     */
    public Learner update(final Long id,
                          final String userName,
                          final Learner learner) {
        return null;
    }

    /**
     *
     * @param id
     * @param userName the userName
     * @return learner
     */
    public Boolean delete(final String userName,
                          final Long id) {
        return false;
    }

    /**
     *
     * @param userName
     * @return learner
     */
    public List<Learner> list(final String userName) {
        return null;
    }

    /**
     * @return learner
     */
    public Integer deleteAll() {
        final String query = "DELETE FROM learner";
        return jdbcTemplate.update(query);
    }
}
