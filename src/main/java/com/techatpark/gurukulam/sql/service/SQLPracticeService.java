package com.techatpark.gurukulam.sql.service;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Practice;
import com.techatpark.gurukulam.sql.service.connector.DatabaseConnector;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SQLPracticeService {

    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;

    /**
     * this is ApplicationContext of Spring.
     */
    private final ApplicationContext applicationContext;

    /**
     * Maps the data from and to the database. return exam
     */
    private final RowMapper<Practice> rowMapper = (rs, rowNum) -> {
        final Practice practice = new Practice();
        practice.setId(rs.getInt("id"));
        practice.setName(rs.getString("name"));
        practice.setDatabase(Database.of(rs.getString("database_type")));
        practice.setScript(rs.getString("script"));
        practice.setDescription(rs.getString("description"));
        return practice;
    };

    /**
     * @param jdbcTemplate
     * @param dataSource
     * @param applicationContext
     */
    public SQLPracticeService(final JdbcTemplate jdbcTemplate, final DataSource dataSource, ApplicationContext applicationContext) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        this.applicationContext = applicationContext;
    }

    /**
     * inserts data to database.
     *
     * @param practice
     * @return practice
     */
    public Optional<Practice> create(final Practice practice) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("practices")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "database_type", "script", "description");
        final Map<String, Object> valueMap = Map.of("name", practice.getName(),
                "database_type", practice.getDatabase().getValue(),
                "script", practice.getScript(),
                "description", practice.getDescription());
        final Number examId = insert.executeAndReturnKey(valueMap);
        Optional<Practice> createdExam = read(examId.intValue());
        createdExam.ifPresent(exam1 -> {
            loadScripts(exam1);
        });
        return createdExam;
    }

    /**
     * Load Scripts into Database.
     *
     * @param practice
     */
    private void loadScripts(final Practice practice) {
        final DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector(practice.getDatabase(),
                applicationContext);
        databaseConnector.loadScript(practice);
    }

    /**
     * Unload Scripts into Database.
     *
     * @param practice
     */
    private void unloadScripts(final Practice practice) {
        final DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector(practice.getDatabase(),
                applicationContext);
        databaseConnector.unloadScript(practice);
    }


    /**
     * read an practice.
     *
     * @param newPracticeId
     * @return practice
     */
    public Optional<Practice> read(final Integer newPracticeId) {
        final String query = "SELECT id,name,script,description,database_type FROM practices WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[]{newPracticeId}, rowMapper));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update database.
     *
     * @param practice
     * @param id
     * @return practice
     * @TODO Soft Delete
     */
    public Optional<Practice> update(final Integer id, final Practice practice) {
        final String query = "UPDATE practices SET name = ?, database_type = ?, script = ? ,description = ? WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query,
                practice.getName(),
                practice.getDatabase().getValue(),
                practice.getScript(), practice.getDescription(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes from database.
     *
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        final Optional<Practice> practice = read(id);
        Boolean success = false;
        if (practice.isPresent()) {

            String query = "DELETE FROM questions WHERE exam_id=?";
            Integer updatedRows = jdbcTemplate.update(query, id);
            query = "DELETE FROM PRACTICES WHERE ID=?";
            updatedRows = jdbcTemplate.update(query, id);
            unloadScripts(practice.get());
            success = !(updatedRows == 0);
        }
        return success;
    }

    /**
     * Cleaning up all practices.
     *
     * @return no.of practices deleted
     */
    public Integer delete() {
        int count = 0;
        List<Practice> practices = list();
        practices.parallelStream().forEach(exam -> delete(exam.getId()));
        return count;
    }

    /**
     * lists all from table .
     *
     * @return list
     */
    public List<Practice> list() {

        String recordsQuery = "SELECT id,name,script,description,database_type FROM practices";

        return jdbcTemplate.query(recordsQuery, rowMapper);
    }

    /**
     * lists all from table as page.
     *
     * @param pageable
     * @return list
     */
    public Page<Practice> page(final Pageable pageable) {

        String recordsQuery = "SELECT id,name,script,description,database_type FROM practices LIMIT "
                + pageable.getPageSize()
                + " OFFSET "
                + ((pageable.getPageNumber() * pageable.getPageSize()));

        String countsQuery = "SELECT COUNT(id) FROM practices";

        return new PageImpl<Practice>(jdbcTemplate.query(recordsQuery, rowMapper), pageable,
                jdbcTemplate.queryForObject(countsQuery, Long.class));
    }

}
