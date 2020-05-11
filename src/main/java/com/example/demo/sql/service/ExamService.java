package com.example.demo.sql.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.sql.model.Exam;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
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
    public ExamService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
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
    public Optional<Exam> create(final Exam exam, final Path[] scriptFiles) throws IOException {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("exams")
                .usingGeneratedKeyColumns("id").usingColumns("name");
        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("name", exam.getName());
        final Number id = insert.executeAndReturnKey(valueMap);
        final Integer examId = id.intValue();
        // create scripts for exams.
        if (scriptFiles != null) {
            for (Path scriptFile : scriptFiles) {
                creatScript(examId, scriptFile);
            }
        }
        return read(examId);
    }

    /**
     * creating script files which are needed for the exams.
     * 
     * @param examId
     * @param scriptFile
     * @return successflag
     * @throws IOException
     */
    private Integer creatScript(final Integer examId, final Path scriptFile) throws IOException {

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("exam_scripts")
                .usingGeneratedKeyColumns("id").usingColumns("exam_id", "script");
        final MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("exam_id", examId);
        in.addValue("script", new SqlLobValue(Files.newInputStream(scriptFile).readAllBytes()), Types.BLOB);
        final Number id = insert.executeAndReturnKey(in);
        return id.intValue();
    }

    /**
     * read an exam.
     * 
     * @param newExamId
     * @return exam
     */
    public Optional<Exam> read(final Integer newExamId) {
        final String query = "SELECT id,name FROM exams WHERE id = ?";
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
        final String query = "UPDATE exams SET name = ? WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, exam.getName(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes from database.
     * 
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        String query = "DELETE FROM exam_scripts WHERE exam_id=?";
        Integer updatedRows = jdbcTemplate.update(query, new Object[] { id });
        query = "DELETE FROM EXAMS WHERE ID=?";
        updatedRows = jdbcTemplate.update(query, new Object[] { id });
        return !(updatedRows == 0);
    }

    /**
     * Cleaning up all exams.
     * 
     * @return no.of exams deleted
     */
    public Integer delete() {
        String query = "DELETE FROM exam_scripts";
        jdbcTemplate.update(query);
        query = "DELETE FROM exams";
        return jdbcTemplate.update(query);
    }

    /**
     * lists all from table.
     * 
     * @param pageNumber
     * @param pageSize
     * @return list
     */
    public List<Exam> list(final Integer pageNumber, final Integer pageSize) {
        String query = "SELECT id,name FROM exams";
        query = query + " LIMIT " + pageSize + " OFFSET " + (pageNumber - 1);
        return jdbcTemplate.query(query, rowMapper);
    }

}
