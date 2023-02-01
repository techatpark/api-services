package com.gurukulams.core.service;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class LearnerProfileService {
    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(LearnerProfileService.class);

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
     *
     * @param anDataSource
     * @param anJdbcTemplate
     */
    public LearnerProfileService(final DataSource anDataSource,
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
    private LearnerProfile rowMapper(final ResultSet rs,
                                     final Integer rowNum)
            throws SQLException {

        LearnerProfile learnerProfile = new LearnerProfile(rs.getString("id"),
                (UUID) rs.getObject("learner_id"),
                rs.getString("first_name"),
                rs.getString("last_name")
        );
        return learnerProfile;
    }

    /**
     * @param userName
     * @param learnerProfile
     * @return learnerProfile
     */
    public LearnerProfile create(final String userName,
                          final LearnerProfile learnerProfile) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("learner_profile")
                .usingColumns("id", "learner_id",
                        "first_name",
                        "last_name");
        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("id", learnerProfile.id());
        valueMap.put("learner_id", learnerProfile.learnerId());
        valueMap.put("first_name", learnerProfile.firstName());
        valueMap.put("last_name", learnerProfile.lastName());

        insert.execute(valueMap);

        final Optional<LearnerProfile> createdLearner = read(userName,
                learnerProfile.id());
        logger.info("Created learner {}", learnerProfile.id());
        return createdLearner.get();
    }

    /**
     * @param userName
     * @param id
     * @return LearnerProfile
     */
    public Optional<LearnerProfile> read(final String userName,
                                  final String id) {
        final String query = "SELECT id,learner_id,first_name,last_name"
                + " FROM learner_profile WHERE id = ?";

        try {
            final LearnerProfile p = jdbcTemplate.queryForObject(query,
                    this::rowMapper, id);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * @param userName
     * @param learnerId
     * @return learnerprofile
     */
    public Optional<LearnerProfile> readByUUID(final String userName,
                                         final UUID learnerId) {
        final String query = "SELECT id,learner_id,first_name,last_name"
                + " FROM learner_profile WHERE learner_id = ?";

        try {
            final LearnerProfile p = jdbcTemplate.queryForObject(query,
                    this::rowMapper, learnerId);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * @param id
     * @param userName
     * @param learnerProfile
     * @return LearnerProfile
     */
    public LearnerProfile update(final String id,
                          final String userName,
                          final LearnerProfile learnerProfile) {
        logger.debug("Entering updating from LearnerProfile {}", id);
        final String query = "UPDATE learner_profile SET first_name=?,"
                + "last_name=? WHERE id=?";
        final Integer updatedRows = jdbcTemplate.update(query,
                learnerProfile.firstName(),
                learnerProfile.lastName(), id);
        if (updatedRows == 0) {
            logger.error("update not found", id);
            throw new IllegalArgumentException("LearnerProfile not found");
        }
        return read(userName, id).get();
    }

    /**
     * @param id
     * @param userName the userName
     * @return LearnerProfile
     */
    public Boolean delete(final String userName,
                          final String id) {
        final String query = "DELETE FROM learner_profile WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }

    /**
     * @param userName
     * @return LearnerProfile
     */
    public List<LearnerProfile> list(final String userName) {
        final String query = "SELECT id,learner_id,"
                + "first_name,last_name FROM learner_profile";
        return jdbcTemplate.query(query, this::rowMapper);
    }

    /**
     * @return learner_profile
     */
    public Integer deleteAll() {
        final String query = "DELETE FROM learner_profile";
        return jdbcTemplate.update(query);
    }

}