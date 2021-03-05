package com.techatpark.gurukulam.sql.service;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Exam;
import com.techatpark.gurukulam.sql.service.connector.DatabaseConnector;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SQLExamService {
    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;

    /**
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public SQLExamService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    /**
     * Maps the data from and to the database. return exam
     */
    private final RowMapper<Exam> rowMapper = (rs, rowNum) -> {
        final Exam exam = new Exam();
        exam.setId(rs.getInt("id"));
        exam.setName(rs.getString("name"));
        exam.setDatabase(Database.of(rs.getString("database_type")));
        return exam;
    };

    /**
     * inserts data to database.
     * 
     * @param exam
     * @param scriptFiles
     * @return exam
     * @throws IOException
     */
    public Optional<Exam> create(final Exam exam, final InputStream[] scriptFiles) throws IOException {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("exams")
                .usingGeneratedKeyColumns("id").usingColumns("name", "database_type");
        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("name", exam.getName());
        valueMap.put("database_type", exam.getDatabase().getValue());
        final Number id = insert.executeAndReturnKey(valueMap);
        final Integer examId = id.intValue();
        final Optional<Exam> createdExam = read(examId);
        // create scripts for exams.
        if (scriptFiles != null) {
            for (final InputStream scriptFile : scriptFiles) {
                creatScript(examId, scriptFile);
            }
            if (createdExam.isPresent()) {
                loadScripts(createdExam.get(), scriptFiles);
            }
        }

        return createdExam;
    }

    /**
     * creating script files which are needed for the exams.
     * 
     * @param examId
     * @param scriptFile
     * @return successflag
     * @throws IOException
     */
    private Integer creatScript(final Integer examId, final InputStream scriptFile) throws IOException {

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("exam_scripts")
                .usingGeneratedKeyColumns("id").usingColumns("exam_id", "script");
        final MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("exam_id", examId);
        in.addValue("script", new SqlLobValue(scriptFile.readAllBytes()), Types.BLOB);
        final Number id = insert.executeAndReturnKey(in);
        return id.intValue();
    }

    /**
     * Load Scripts into Database.
     * 
     * @param exam
     * @param scriptFiles
     */
    private void loadScripts(final Exam exam, final InputStream[] scriptFiles) {
        final DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector(exam.getDatabase(),
                jdbcTemplate);
        databaseConnector.loadScript(exam, scriptFiles);
    }

    /**
     * Unload Scripts into Database.
     * 
     * @param exam
     * 
     */
    private void unloadScripts(final Exam exam) {
        final DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector(exam.getDatabase(),
                jdbcTemplate);
        databaseConnector.unloadScript(exam.getId());
    }

    /**
     * read an exam.
     * 
     * @param newExamId
     * @return exam
     */
    public Optional<Exam> read(final Integer newExamId) {
        final String query = "SELECT id,name,database_type FROM exams WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { newExamId }, rowMapper));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update database.
     * 
     * @TODO Soft Delete
     * @param exam
     * @param id
     * @return exam
     */
    public Optional<Exam> update(final Integer id, final Exam exam) {
        final String query = "UPDATE exams SET name = ?, database_type = ? WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, exam.getName(), exam.getDatabase().getValue(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes from database.
     * 
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        final Optional<Exam> exam = read(id);
        Boolean success = false;
        if (exam.isPresent()) {
            String query = "DELETE FROM exam_scripts WHERE exam_id=?";
            Integer updatedRows = jdbcTemplate.update(query, new Object[] { id });
            query = "DELETE FROM questions WHERE exam_id=?";
            updatedRows = jdbcTemplate.update(query, new Object[] { id });
            query = "DELETE FROM EXAMS WHERE ID=?";
            updatedRows = jdbcTemplate.update(query, new Object[] { id });
            success = !(updatedRows == 0);
            if (success) {
                unloadScripts(exam.get());
            }
        }
        return success;
    }

    /**
     * Cleaning up all exams.
     * 
     * @return no.of exams deleted
     */
    public Integer delete() {
        String query = "DELETE FROM exam_scripts";
        jdbcTemplate.update(query);
        query = "DELETE FROM questions";
        jdbcTemplate.update(query);
        query = "DELETE FROM exams";
        return jdbcTemplate.update(query);
    }

    /**
     * lists all from table.
     *
     * @param pageable
     * @return list
     */
    public Page<Exam> list(final Pageable pageable) {

        String columnNames = "id,name,database_type";
 
        String recordsQuery = "SELECT " 
        + columnNames 
        + " FROM exams"
        + " LIMIT " 
        + pageable.getPageSize()
        + " OFFSET " 
        + pageable.getPageNumber();

        String countsQuery = "SELECT " 
        + "COUNT(id)" 
        + " FROM exams"
        + " LIMIT "
                + pageable.getPageSize()
        + " OFFSET "
                + pageable.getPageNumber();

        return new PageImpl<Exam>(jdbcTemplate.query(recordsQuery, rowMapper), pageable, jdbcTemplate.queryForObject(
            countsQuery,  Long.class));
    }

}
