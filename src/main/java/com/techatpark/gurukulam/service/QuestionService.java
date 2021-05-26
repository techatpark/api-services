package com.techatpark.gurukulam.service;

import com.techatpark.gurukulam.model.Question;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuestionService {
    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * this creates connection functionalities.
     */
    private final DataSource dataSource;
    /**
     * Maps the data from and to the database. return question.
     */
    private final RowMapper<Question> rowMapper = (rs, rowNum) -> {
        final Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setExamId(rs.getInt("exam_id"));
        question.setQuestion(rs.getString("question"));
        question.setType(rs.getString("type"));
        question.setAnswer(rs.getString("answer"));
        return question;
    };

    /**
     * initializes.
     *
     * @param aJdbcTemplate
     * @param aDataSource
     */
    public QuestionService(final JdbcTemplate aJdbcTemplate,
                           final DataSource aDataSource) {
        this.jdbcTemplate = aJdbcTemplate;
        this.dataSource = aDataSource;
    }

    /**
     * inserts data.
     *
     * @param question
     * @param practiceId
     * @param type
     * @return question
     */
    public Optional<Question> create(final Integer practiceId,
                                     final String type,
                                     final Question question) {
        final SimpleJdbcInsert insert =
                new SimpleJdbcInsert(dataSource).withTableName("questions")
                        .usingGeneratedKeyColumns("id")
                        .usingColumns("exam_id", "question", "type", "answer");

        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("exam_id", practiceId);
        valueMap.put("question", question.getQuestion());
        valueMap.put("type", type);
        valueMap.put("answer", question.getAnswer());
        final Number id = insert.executeAndReturnKey(valueMap);
        return read(id.intValue());
    }

    /**
     * reads from question with given id.
     *
     * @param id
     * @return question
     */
    public Optional<Question> read(final Integer id) {
        final String query =
                "SELECT id,exam_id,question,type,answer FROM questions WHERE"
                        + " id = ?";
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(query, new Object[]{id}, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * updates question with id.
     *
     * @param id
     * @param question
     * @param examId
     * @return question
     */
    public Optional<Question> update(final Integer examId, final Integer id,
                                     final Question question) {
        final String query =
                "UPDATE questions SET exam_id = ?,"
                        + " question = ?, answer = ? WHERE id = ?";
        Integer updatedRows =
                jdbcTemplate.update(query, examId, question.getQuestion(),
                        question.getAnswer(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes from database.
     *
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        String query = "DELETE FROM questions WHERE ID=?";
        Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }

    /**
     * Cleaning up all exams.
     *
     * @return no.of exams deleted
     */
    public Integer delete() {
        final String query = "DELETE FROM questions";
        return jdbcTemplate.update(query);
    }

    /**
     * List questions of exam.
     *
     * @param userName
     * @param practiceId
     * @return quetions in given exam
     */
    public List<Question> list(final String userName,
                               final Integer practiceId) {
        String query = "SELECT q.id,q.exam_id,q.question,q.type,"
                + "CASE p.owner WHEN ? THEN q.answer ELSE NULL END AS answer "
                + "FROM questions as q JOIN practices AS p "
                + "ON q.exam_id = p.id where q.exam_id = ? order by q.id";
        return jdbcTemplate.query(query, rowMapper, userName, practiceId);
    }

    /**
     * list of question.
     *
     * @param pageNumber
     * @param pageSize
     * @return question
     */
    public List<Question> list(final Integer pageNumber,
                               final Integer pageSize) {
        String query = "SELECT id,exam_id,question,type,answer FROM questions";
        query = query + " LIMIT " + pageSize + " OFFSET " + (pageNumber - 1);
        return jdbcTemplate.query(query, rowMapper);
    }
}
