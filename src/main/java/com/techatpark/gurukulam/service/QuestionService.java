package com.techatpark.gurukulam.service;

import com.techatpark.gurukulam.model.Question;
import com.techatpark.gurukulam.model.QuestionChoice;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Question service.
 */
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
     * Maps the data from and to the database. return question.
     */
    private final RowMapper<QuestionChoice> rowMapperQuestionChoice = (
            rs, rowNum) -> {
        final QuestionChoice questionChoice = new QuestionChoice();
        questionChoice.setId(rs.getInt("id"));
        questionChoice.setQuestionId(rs.getInt("question_id"));
        questionChoice.setValue(rs.getString("value"));
        return questionChoice;
    };

    /**
     * initializes.
     *
     * @param aJdbcTemplate the a jdbc template
     * @param aDataSource   the a data source
     */
    public QuestionService(final JdbcTemplate aJdbcTemplate,
                           final DataSource aDataSource) {
        this.jdbcTemplate = aJdbcTemplate;
        this.dataSource = aDataSource;
    }

    /**
     * inserts data.
     *
     * @param practiceId the practice id
     * @param type       the type
     * @param question   the question
     * @return question optional
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

        if (question.getType().equals("CHOOSETHEBEST")
                && question.getQuestionChoice() != null) {
            final SimpleJdbcInsert insertQuestionChoice =
                    new SimpleJdbcInsert(dataSource)
                            .withTableName("question_choices")
                            .usingGeneratedKeyColumns("id")
                            .usingColumns("question_id", "value");

            question.getQuestionChoice().forEach(questionChoice -> {
                Map<String, Object> valueMapQuestionChoice = new HashMap<>();
                valueMapQuestionChoice.put("question_id", id);
                valueMapQuestionChoice.put("value", questionChoice.getValue());

                insertQuestionChoice
                        .executeAndReturnKey(valueMapQuestionChoice);
            });

        }

        return read(id.intValue());
    }

    /**
     * List question choice list.
     *
     * @param questionChoiceId the question choice id
     * @return the list
     */
    public List<QuestionChoice> listQuestionChoice(
            final Integer questionChoiceId) {
        final String query =
                "SELECT id,question_id,value FROM question_choices WHERE"
                        + " question_id = ?";
        return jdbcTemplate.query(query, rowMapperQuestionChoice,
                questionChoiceId);
    }

    /**
     * reads from question with given id.
     *
     * @param id the id
     * @return question optional
     */
    public Optional<Question> read(final Integer id) {
        final String query =
                "SELECT id,exam_id,question,type,answer FROM questions WHERE"
                        + " id = ?";
        try {

            Question question = jdbcTemplate
                    .queryForObject(query, new Object[]{id}, rowMapper);
            if (question.getType().equals("CHOOSETHEBEST")) {
                question.setQuestionChoice(
                        listQuestionChoice(question.getId()));
            }
            return Optional.of(question);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    /**
     * updates question with id.
     *
     * @param examId   the exam id
     * @param id       the id
     * @param question the question
     * @return question optional
     */
    public Optional<Question> update(final Integer examId, final Integer id,
                                     final Question question) {
        final String query =
                "UPDATE questions SET exam_id = ?,"
                        + " question = ?, answer = ? WHERE id = ?";
        final Integer updatedRows =
                jdbcTemplate.update(query, examId, question.getQuestion(),
                        question.getAnswer(), id);

        if (question.getType().equals("CHOOSETHEBEST")
                && question.getQuestionChoice() != null) {


            final SimpleJdbcInsert insertQuestionChoice =
                    new SimpleJdbcInsert(dataSource)
                            .withTableName("question_choices")
                            .usingGeneratedKeyColumns("id")
                            .usingColumns("question_id", "value");

            List<Integer> availableIds = new ArrayList<>();

            question.getQuestionChoice().forEach(questionChoice -> {
                if (questionChoice.getId() == null) {
                    Map<String, Object> valueMapQuestionChoice =
                            new HashMap<>();
                    valueMapQuestionChoice.put("question_id", id);
                    valueMapQuestionChoice
                            .put("value", questionChoice.getValue());

                    insertQuestionChoice
                            .executeAndReturnKey(valueMapQuestionChoice);
                } else {
                    availableIds.add(questionChoice.getId());
                    final String updatequestionChoice =
                            "UPDATE question_choices SET value = ? "
                                    + "WHERE id = ?";
                    jdbcTemplate.update(updatequestionChoice,
                            questionChoice.getValue(),
                            questionChoice.getId());
                }

                if (!availableIds.isEmpty()) {
                    final String deletequestionChoice =
                            "DELETE question_choices "
                                    + "WHERE id NOT IN ( "
                                    + availableIds.stream()
                                            .map(aId -> "?")
                                            .collect(Collectors.joining(","))
                                    + " ) ";
                    jdbcTemplate.update(deletequestionChoice,
                            availableIds.toArray());
                }

            });

        }
        return updatedRows == 0 ? null : read(id);

    }

    /**
     * deletes from database.
     *
     * @param id the id
     * @return successflag boolean
     */
    public Boolean delete(final Integer id) {
        final String query = "DELETE FROM questions WHERE ID=?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }

    /**
     * delete all records from questionchoice with the given question id.
     *
     * @param questionId
     * @return successflag boolean
     */
    public Boolean deleteQuestionChoice(final Integer questionId) {
        final String query =
                "DELETE FROM question_choices WHERE question_id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, questionId);
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
     * @param userName   the user name
     * @param practiceId the practice id
     * @return quetions in given exam
     */
    public List<Question> list(final String userName,
                               final Integer practiceId) {
        final String query = "SELECT q.id,q.exam_id,q.question,q.type,"
                + "CASE p.owner WHEN ? THEN q.answer ELSE NULL END AS answer "
                + "FROM questions as q JOIN practices AS p "
                + "ON q.exam_id = p.id where q.exam_id = ? order by q.id";
        return jdbcTemplate.query(query, rowMapper, userName, practiceId);
    }

    /**
     * list of question.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return question list
     */
    public List<Question> list(final Integer pageNumber,
                               final Integer pageSize) {
        String query = "SELECT id,exam_id,question,type,answer FROM questions";
        query = query + " LIMIT " + pageSize + " OFFSET " + (pageNumber - 1);
        return jdbcTemplate.query(query, rowMapper);
    }
}
