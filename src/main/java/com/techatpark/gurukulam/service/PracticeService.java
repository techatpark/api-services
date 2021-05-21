package com.techatpark.gurukulam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.techatpark.gurukulam.model.Practice;
import com.techatpark.gurukulam.model.sql.SqlPractice;
import com.techatpark.gurukulam.service.connector.DatabaseConnector;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     * this is ObjectMapper of Spring.
     */
    private final ObjectMapper objectMapper;


    /**
     * @param aJdbcTemplate
     * @param aDatasource
     * @param anApplicationContext
     * @param aObjectMapper
     */
    public PracticeService(final JdbcTemplate aJdbcTemplate,
                           final DataSource aDatasource,
                           final ApplicationContext anApplicationContext,
                           final ObjectMapper aObjectMapper) {
        this.jdbcTemplate = aJdbcTemplate;
        this.dataSource = aDatasource;
        this.applicationContext = anApplicationContext;
        this.objectMapper = aObjectMapper;
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @param <T>
     * @return p
     * @throws SQLException
     */
    private <T extends Practice> T rowMapper(final ResultSet rs,
                                             final Integer rowNum)
            throws SQLException {
        String metaData = rs.getString("meta_data");
        Practice practice;
        if (metaData == null) {
            practice = new Practice();
        } else {
            try {
                practice = new ObjectMapper().readValue(metaData,
                        getPracticeClass(rs.getString("type")));
            } catch (JsonProcessingException e) {
                practice = new Practice();
            }
        }
        practice.setId(rs.getInt("id"));
        practice.setName(rs.getString("name"));
        practice.setOwner(rs.getString("owner"));
        practice.setDescription(rs.getString("description"));
        return (T) practice;
    }

    /**
     * Gets Class for Practice Type.
     *
     * @param type
     * @return clz.
     */
    private Class<? extends Practice> getPracticeClass(final String type) {
        switch (type) {
            case "sql":
                return SqlPractice.class;
            default:
                return Practice.class;
        }
    }

    private String getMetadata(final Practice practice)
            throws JsonProcessingException {
        if (!practice.getClass().equals(Practice.class)) {
            ObjectNode oNode = objectMapper.valueToTree(practice);
            oNode.remove("id");
            oNode.remove("name");
            oNode.remove("description");
            return objectMapper.writeValueAsString(oNode);
        }
        return null;
    }

    /**
     * inserts data to database.
     *
     * @param type
     * @param owner
     * @param practice
     * @param <T>
     * @return p.
     * @throws JsonProcessingException
     */
    public <T extends Practice> Optional<T> create(final String type,
                                                   final String owner,
                                                   final T practice)
            throws JsonProcessingException {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("practices")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name",
                        "type",
                        "owner",
                        "description",
                        "meta_data");
        final String metaData = getMetadata(practice);
        final Map<String, Object> valueMap = metaData == null
                ? Map.of("name",
                practice.getName(),
                "type", type,
                "owner", owner,
                "description", practice.getDescription())
                : Map.of("name",
                practice.getName(),
                "type", type,
                "owner", owner,
                "description", practice.getDescription(),
                "meta_data", metaData);
        final Number examId = insert.executeAndReturnKey(valueMap);
        Optional<T> createdExam = read(examId.intValue());
        createdExam.ifPresent(exam1 -> {
            if (exam1 instanceof SqlPractice) {
                loadScripts((SqlPractice) exam1);
            }

        });
        return createdExam;
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
     * @param <T>
     * @return p.
     */
    public <T extends Practice> Optional<T> read(final Integer newPracticeId) {
        final String query =
                "SELECT id,name,owner,type,meta_data,description "
                        + "FROM practices WHERE id = ?";


        try {
            T p = (T) jdbcTemplate
                    .queryForObject(query, new Object[]{newPracticeId},
                            this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update database.
     *
     * @param id
     * @param practice
     * @param <T>
     * @return p.
     * @throws JsonProcessingException
     */
    public <T extends Practice> Optional<T> update(final Integer id,
                                                   final T practice)
            throws JsonProcessingException {
        final String query =
                "UPDATE practices SET name = ?, meta_data = ? ,"
                        + "description = ? WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query,
                practice.getName(),
                getMetadata(practice),
                practice.getDescription(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes from database.
     *
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        final Optional<Practice> oPractice = read(id);
        Boolean success = false;
        if (oPractice.isPresent()) {

            String query = "DELETE FROM questions WHERE exam_id=?";
            Integer updatedRows = jdbcTemplate.update(query, id);
            query = "DELETE FROM PRACTICES WHERE ID=?";
            updatedRows = jdbcTemplate.update(query, id);
            Practice practice = oPractice.get();
            if (practice instanceof SqlPractice) {
                unloadScripts((SqlPractice) practice);
            }
            success = !(updatedRows == 0);
        }
        return success;
    }

    /**
     * Cleaning up all practices.
     *
     * @param type
     * @return no.of practices deleted
     */
    public Integer delete(final String type) {
        int count = 0;
        List<SqlPractice> practices = list(type);
        practices.parallelStream().forEach(exam -> delete(exam.getId()));
        return count;
    }

    /**
     * lists all from table.
     *
     * @param type
     * @param <T>
     * @return lp
     */
    public <T extends Practice> List<T> list(final String type) {

        String recordsQuery =
                "SELECT id,name,owner,type,meta_data,description"
                        + " FROM practices where type = ?";
        List<T> tList =
                jdbcTemplate.query(recordsQuery, this::rowMapper, type);
        return tList;
    }

    /**
     * lists all from table as page.
     *
     * @param type
     * @param pageable
     * @param <T>
     * @return lp
     */
    public <T extends Practice> Page<T> page(final String type,
                                             final Pageable pageable) {

        String recordsQuery =
                "SELECT id,name,owner,type,meta_data,description"
                        + " FROM practices where type = ? LIMIT "
                        + pageable.getPageSize()
                        + " OFFSET "
                        +
                        ((pageable.getPageNumber() * pageable.getPageSize()));

        String countsQuery = "SELECT COUNT(id) FROM practices where type = ?";

        return new PageImpl<T>(
                jdbcTemplate.query(recordsQuery, this::rowMapper, type),
                pageable,
                jdbcTemplate.queryForObject(countsQuery, Long.class, type));
    }

}
