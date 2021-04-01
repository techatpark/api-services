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
        final Practice exam = new Practice();
        exam.setId(rs.getInt("id"));
        exam.setName(rs.getString("name"));
        exam.setDatabase(Database.of(rs.getString("database_type")));
        exam.setScript(rs.getString("script"));
        return exam;
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
     * @param exam
     * @return exam
     */
    public Optional<Practice> create(final Practice exam) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("exams")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "database_type", "script");
        final Map<String, Object> valueMap = Map.of("name", exam.getName(),
                "database_type", exam.getDatabase().getValue(),
                "script", exam.getScript());
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
     * @param exam
     */
    private void loadScripts(final Practice exam) {
        final DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector(exam.getDatabase(),
                applicationContext);
        databaseConnector.loadScript(exam);
    }

    /**
     * Unload Scripts into Database.
     *
     * @param exam
     */
    private void unloadScripts(final Practice exam) {
        final DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector(exam.getDatabase(),
                applicationContext);
        databaseConnector.unloadScript(exam.getId());
    }


    /**
     * read an exam.
     *
     * @param newExamId
     * @return exam
     */
    public Optional<Practice> read(final Integer newExamId) {
        final String query = "SELECT id,name,script,database_type FROM exams WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[]{newExamId}, rowMapper));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update database.
     *
     * @param exam
     * @param id
     * @return exam
     * @TODO Soft Delete
     */
    public Optional<Practice> update(final Integer id, final Practice exam) {
        final String query = "UPDATE exams SET name = ?, database_type = ?, script = ? WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query,
                exam.getName(),
                exam.getDatabase().getValue(),
                exam.getScript(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes from database.
     *
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        final Optional<Practice> exam = read(id);
        Boolean success = false;
        if (exam.isPresent()) {
            unloadScripts(exam.get());
            String query = "DELETE FROM questions WHERE exam_id=?";
            Integer updatedRows = jdbcTemplate.update(query, id);
            query = "DELETE FROM EXAMS WHERE ID=?";
            updatedRows = jdbcTemplate.update(query, id);
            success = !(updatedRows == 0);
        }
        return success;
    }

    /**
     * Cleaning up all exams.
     *
     * @return no.of exams deleted
     */
    public Integer delete() {
        int count = 0 ;
        List<Practice> exams = list();
        exams.parallelStream().forEach(exam -> delete(exam.getId()));
        return count;
    }

    /**
     * lists all from table .
     * @return list
     */
    public List<Practice> list() {

        String recordsQuery = "SELECT id,name,script,database_type FROM exams";

        return jdbcTemplate.query(recordsQuery, rowMapper);
    }

    /**
     * lists all from table as page.
     *
     * @param pageable
     * @return list
     */
    public Page<Practice> page(final Pageable pageable) {

        String recordsQuery = "SELECT id,name,script,database_type FROM exams LIMIT "
                + pageable.getPageSize()
                + " OFFSET "
                + ((pageable.getPageNumber() * pageable.getPageSize()));

        String countsQuery = "SELECT COUNT(id) FROM exams";

        return new PageImpl<Practice>(jdbcTemplate.query(recordsQuery, rowMapper), pageable,
                jdbcTemplate.queryForObject(countsQuery, Long.class));
    }

}
