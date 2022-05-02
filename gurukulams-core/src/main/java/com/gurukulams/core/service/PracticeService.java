package com.gurukulams.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gurukulams.core.service.connector.DatabaseConnector;
import com.gurukulams.core.model.Practice;
import com.gurukulams.core.model.sql.SqlPractice;
import com.gurukulams.core.util.PropertyPlaceholderExposer;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The type Practice service.
 */
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
     * Reade Properties.
     */
    private final PropertyPlaceholderExposer propertyPlaceholderExposer;
    /**
     * this is ApplicationContext of Spring.
     */
    private final ApplicationContext applicationContext;

    /**
     * this is ObjectMapper of Spring.
     */
    private final ObjectMapper objectMapper;

    /**
     * Validator.
     */
    private final Validator validator;


    /**
     * Instantiates a new Practice service.
     *
     * @param aJdbcTemplate        the a jdbc template
     * @param aDatasource          the a datasource
     * @param anApplicationContext the an application context
     * @param aPExpo               the propery expser
     * @param aObjectMapper        the a object mapper
     * @param aValidator           the validator
     */
    public PracticeService(
            final JdbcTemplate aJdbcTemplate,
            final DataSource aDatasource,
            final ApplicationContext anApplicationContext,
            final PropertyPlaceholderExposer aPExpo,
            final ObjectMapper aObjectMapper,
            final Validator aValidator) {
        this.jdbcTemplate = aJdbcTemplate;
        this.dataSource = aDatasource;
        this.applicationContext = anApplicationContext;
        this.objectMapper = aObjectMapper;
        this.propertyPlaceholderExposer = aPExpo;
        this.validator = aValidator;
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
        final String metaData = rs.getString("meta_data");
        Practice practice;
        if (metaData == null) {
            practice = new Practice();
        } else {
            try {
                practice = new ObjectMapper().readValue(metaData,
                        getPracticeClass(rs.getString("type")));
            } catch (final JsonProcessingException e) {
                practice = new Practice();
            }
        }
        practice.setId(rs.getInt("id"));
        practice.setName(rs.getString("name"));
        practice.setCreatedBy(rs.getString("created_by"));
        practice.setDescription(rs.getString("description"));


        LocalDate calendarDate = rs.getDate("created_at")
                .toLocalDate();
        ZonedDateTime zdt = calendarDate.atStartOfDay(ZoneId
                .of("Europe/Paris"));


        practice.setCreatedAt(zdt.toInstant());
        Date sqlDate = rs.getDate("modified_at");

        if (sqlDate != null) {
            calendarDate = sqlDate.toLocalDate();
            zdt = calendarDate.atStartOfDay(ZoneId.of("Europe/Paris"));
            practice.setUpdatedAt(zdt.toInstant());
        }
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
            final ObjectNode oNode = objectMapper.valueToTree(practice);
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
     * @param <T>      the type parameter
     * @param type     the type
     * @param createdBy    the createdBy
     * @param locale    the locale
     * @param practice the practice
     * @return p. optional
     * @throws JsonProcessingException the json processing exception
     */
    public <T extends Practice> Optional<T> create(final String type,
                                                   final String createdBy,
                                                   final Locale locale,
                                                   final T practice)
            throws JsonProcessingException {
        return create(type, createdBy, locale, null, practice);
    }

    /**
     * inserts data to database.
     *
     * @param <T>      the type parameter
     * @param type     the type
     * @param createdBy    the createdBy
     * @param locale     the locale
     * @param book     the book
     * @param practice the practice
     * @return p. optional
     * @throws JsonProcessingException the json processing exception
     */
    private <T extends Practice> Optional<T> create(final String type,
                                                    final String createdBy,
                                                    final Locale locale,
                                                    final String book,
                                                    final T practice)
            throws JsonProcessingException {

        Set<ConstraintViolation<Practice>> violations = validator
                .validate(practice);
        if (violations.isEmpty()) {
            final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                    .withTableName("practices")
                    .usingGeneratedKeyColumns("id")
                    .usingColumns("name",
                            "type",
                            "created_by",
                            "book",
                            "description",
                            "meta_data");
            final String metaData = getMetadata(practice);
            final Map<String, Object> valueMap = new HashMap<>(6);
            valueMap.put("name",
                    practice.getName());
            valueMap.put("type", type);
            valueMap.put("created_by", createdBy);
            valueMap.put("book", book);
            valueMap.put("description", practice.getDescription());
            valueMap.put("meta_data", metaData);
            final Number examId = insert.executeAndReturnKey(valueMap);
            final Optional<T> createdExam = read(examId.intValue());
            createdExam.ifPresent(exam1 -> {
                if (exam1 instanceof SqlPractice) {
                    loadScripts((SqlPractice) exam1);
                }

            });
            return createdExam;
        } else {
            throw new ConstraintViolationException(violations);
        }

    }

    /**
     * get pracice for a bookName.
     * create if not exists alredy.
     *
     * @param bookName
     * @param locale
     * @return pracice
     */
    public Practice getQuestionBank(final String bookName, final Locale locale)
            throws JsonProcessingException {
        Optional<Practice> oPractice = readByBook(bookName);

        if (oPractice.isEmpty()) {
            Practice practice = new Practice();
            practice.setName("Book:$" + bookName);
            practice.setDescription("Question Bank for the bookName "
                    + bookName);
            oPractice = create(bookName,
                    getOwnerName(bookName), locale, bookName,
                    practice);
        }

        return oPractice.get();
    }

    private String getOwnerName(final String bookName) {
        return propertyPlaceholderExposer.get("admins." + bookName);
    }

    private <T extends Practice> Optional<T> readByBook(
                                            final String newBook) {
        final String query =
                "SELECT id,name,created_by,type,meta_data,description,"
                        + "created_at,modified_at "
                        + "FROM practices WHERE book = ?";


        try {
            final T p = (T) jdbcTemplate
                    .queryForObject(query, new Object[]{newBook},
                            this::rowMapper);
            return Optional.of(p);
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
     * @param <T>           the type parameter
     * @param newPracticeId the new practice id
     * @return p. optional
     */
    public <T extends Practice> Optional<T> read(final Integer newPracticeId) {
        final String query = "SELECT id,name,created_by,type,meta_data,"
                        + "description,created_at,modified_at "
                        + "FROM practices WHERE id = ?";


        try {
            final T p = (T) jdbcTemplate
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
     * @param <T>      the type parameter
     * @param id       the id
     * @param practice the practice
     * @return p. optional
     * @throws JsonProcessingException the json processing exception
     */
    public <T extends Practice> Optional<T> update(final Integer id,
                                                   final T practice)
            throws JsonProcessingException {
        Set<ConstraintViolation<Practice>> violations = validator
                .validate(practice);
        if (violations.isEmpty()) {
            final String query =
                    "UPDATE practices SET name=?, meta_data=?,"
                            + "description=?, modified_at=CURRENT_TIMESTAMP "
                            + "WHERE id = ?";
            final Integer updatedRows = jdbcTemplate.update(query,
                    practice.getName(),
                    getMetadata(practice),
                    practice.getDescription(), id);
            return updatedRows == 0 ? null : read(id);
        } else {
            throw new ConstraintViolationException(violations);
        }

    }

    /**
     * deletes from database.
     *
     * @param id the id
     * @return successflag boolean
     */
    public Boolean delete(final Integer id) {
        final Optional<Practice> oPractice = read(id);
        Boolean success = false;
        if (oPractice.isPresent()) {

            String query = "DELETE FROM questions WHERE exam_id=?";
            jdbcTemplate.update(query, id);
            query = "DELETE FROM PRACTICES WHERE ID=?";
            final Integer updatedRows = jdbcTemplate.update(query, id);
            final Practice practice = oPractice.get();
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
     * @param type the type
     * @return no.of practices deleted
     */
    public Integer delete(final String type) {
        final int count = 0;
        final List<Practice> practices = list(type);
        practices.parallelStream().forEach(exam -> delete(exam.getId()));
        return count;
    }

    /**
     * lists all from table.
     *
     * @param <T>  the type parameter
     * @param type the type
     * @return lp list
     */
    public <T extends Practice> List<T> list(final String type) {

        final String recordsQuery =
                "SELECT id,name,created_by,type,meta_data,description,"
                        + "created_at,modified_at"
                        + " FROM practices where type = ?";
        final List<T> tList =
                jdbcTemplate.query(recordsQuery, this::rowMapper, type);
        return tList;
    }

    /**
     * lists all from table as page.
     *
     * @param <T>      the type parameter
     * @param type     the type
     * @param pageable the pageable
     * @return lp page
     */
    public <T extends Practice> Page<T> page(final String type,
                                             final Pageable pageable) {

        final String recordsQuery =
                "SELECT id,name,created_by,type,meta_data,description,"
                        + "created_at,modified_at"
                        + " FROM practices where type = ? LIMIT "
                        + pageable.getPageSize()
                        + " OFFSET "
                        + ((pageable.getPageNumber() * pageable.getPageSize()));

        final String countsQuery = "SELECT COUNT(id) FROM practices"
                + " where type = ?";

        return new PageImpl<T>(
                jdbcTemplate.query(recordsQuery, this::rowMapper, type),
                pageable,
                jdbcTemplate.queryForObject(countsQuery, Long.class, type));
    }

    /**
     * Checks if given user is created_by of the book.
     *
     * @param userName
     * @param bookName
     * @return isOwner
     */
    public boolean isOwner(final String userName, final String bookName) {
        return getOwnerName(bookName).equals(userName);
    }


    /**
     * Cleaning up all practices.
     *
     * @return no.of exams deleted
     */
    public Integer delete() {
        final String query = "DELETE FROM practices";
        return jdbcTemplate.update(query);
    }
}
