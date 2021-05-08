package com.techatpark.gurukulam.sql.service;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.sql.SqlPractice;
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
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PracticeService {

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
    private final RowMapper<SqlPractice> rowMapper = (rs, rowNum) -> {
        final SqlPractice practice = new SqlPractice();
        practice.setId(rs.getInt("id"));
        practice.setName(rs.getString("name"));
        practice.setDescription(rs.getString("description"));
        return practice;
    };

    private final RowMapper<SqlPractice> rowMapperPracticeSql =
            (rs, rowNum) -> {
        final SqlPractice practice = new SqlPractice();
        practice.setExamId(rs.getInt("exam_id"));
        practice.setScript(rs.getString("script"));
        practice.setDatabase(Database.valueOf(rs.getString("database_type")));
        return practice;
    };


    /**
     * @param aJdbcTemplate
     * @param aDatasource
     * @param anApplicationContext
     */
    public PracticeService(final JdbcTemplate aJdbcTemplate,
                           final DataSource aDatasource,
                           final ApplicationContext anApplicationContext) {
        this.jdbcTemplate = aJdbcTemplate;
        this.dataSource = aDatasource;
        this.applicationContext = anApplicationContext;
    }

    /**
     * inserts data to database.
     *
     * @param practice
     * @return practice
     */
    @Transactional
    public Optional<SqlPractice> create(final SqlPractice practice) {
    //insert into practices.
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("practices")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "description");
        final Map<String, Object> valueMap = Map.of("name", practice.getName(),
                "description", practice.getDescription());
        final Number examId = insert.executeAndReturnKey(valueMap);
        if(examId.intValue() == 0) {
            throw new RuntimeException("throwing exception to test " +
                    "transaction rollback");
        }

        final SimpleJdbcInsert insertToPracticesSql =
                new SimpleJdbcInsert(dataSource)
                .withTableName("practices_sql")
                .usingColumns("exam_id", "database_type", "script");
        final Map<String, Object> valueMapPracticeSql = Map.of("exam_id",
                examId,
                "database_type", practice.getDatabase(),
                "script", practice.getScript());
        int inserted = insertToPracticesSql.execute(valueMapPracticeSql);
        if(inserted != 1) {
            throw new RuntimeException("throwing exception to test " +
                    "transaction rollback");
        }

        Optional<SqlPractice> createdExam = readPracticeSql(examId.intValue());
        createdExam.ifPresent(exam1 -> {
            loadScripts(exam1);
        });
        return createdExam;
    }

    /**
     * read an practice.
     *
     * @param anExamId
     * @return sqlpractice
     */
    public Optional<SqlPractice> readPracticeSql(final Integer anExamId) {
        final String query =
                "SELECT exam_id,database_type,script "
                        + "FROM practices_sql WHERE exam_id = ?";
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(query, new Object[]{anExamId},
                            rowMapperPracticeSql));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * Load Scripts into Database.
     *
     * @param practice
     */
    private void loadScripts(final SqlPractice practice) {
        final DatabaseConnector databaseConnector =
                DatabaseConnector.getDatabaseConnector(practice.getDatabase(),
                        applicationContext);
        databaseConnector.loadScript(practice);
    }

    /**
     * Unload Scripts into Database.
     *
     * @param practice
     */
    private void unloadScripts(final SqlPractice practice) {
        final DatabaseConnector databaseConnector =
                DatabaseConnector.getDatabaseConnector(practice.getDatabase(),
                        applicationContext);
        databaseConnector.unloadScript(practice);
    }


    /**
     * read an practice.
     *
     * @param newPracticeId
     * @return practice
     */
    public Optional<SqlPractice> read(final Integer newPracticeId) {
        final String query =
                "SELECT id,name,description "
                        + "FROM practices WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(query, new Object[]{newPracticeId},
                            rowMapper));
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
    public Optional<SqlPractice> update(final Integer id,
                                     final SqlPractice practice) {
        final String query =
                "UPDATE practices SET name = ?, database_type = ?, script = ? ,"
                        + "description = ? WHERE id = ?";
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
        final Optional<SqlPractice> practice = read(id);
        Boolean success = false;
        if (practice.isPresent()) {

            String query = "DELETE FROM practices_sql WHERE exam_id=?";
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
        List<SqlPractice> practices = list();
        practices.parallelStream().forEach(exam -> delete(exam.getId()));
        return count;
    }

    /**
     * lists all from table .
     *
     * @return list
     */
    public List<SqlPractice> list() {

        String recordsQuery =
                "SELECT id,name,description"
                        + " FROM practices";

        return jdbcTemplate.query(recordsQuery, rowMapper);
    }

    /**
     * lists all from table as page.
     *
     * @param pageable
     * @return list
     */
    public Page<SqlPractice> page(final Pageable pageable) {

        String recordsQuery =
                "SELECT id,name,script,description,database_type"
                        + " FROM practices LIMIT "
                        + pageable.getPageSize()
                        + " OFFSET "
                        +
                        ((pageable.getPageNumber() * pageable.getPageSize()));

        String countsQuery = "SELECT COUNT(id) FROM practices";

        return new PageImpl<SqlPractice>(
                jdbcTemplate.query(recordsQuery, rowMapper), pageable,
                jdbcTemplate.queryForObject(countsQuery, Long.class));
    }

}
