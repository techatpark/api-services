package com.gurukulams.gurukulam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.gurukulam.model.Choice;
import com.gurukulams.gurukulam.model.Practice;
import com.gurukulams.gurukulam.model.Question;
import com.gurukulams.gurukulam.model.QuestionType;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.ElementType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
     * this helps to practiceService.
     */
    private final PracticeService practiceService;

    /**
     * Validator.
     */
    private final Validator validator;

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
        question.setType(QuestionType.valueOf(rs.getString("type")));
        question.setAnswer(rs.getString("answer"));
        return question;
    };
    /**
     * Maps the data from and to the database. return question.
     */
    private final RowMapper<Choice> rowMapperQuestionChoice = (
            rs, rowNum) -> {
        final Choice choice = new Choice();
        choice.setId(rs.getInt("id"));
        choice.setValue(rs.getString("value"));
        choice.setAnswer(rs.getBoolean("is_answer"));
        // https://docs.oracle.com/javase/7/docs/api/java/sql
        // /ResultSet.html#wasNull%28%29
        if (rs.wasNull()) {
            choice.setAnswer(null);
        }
        return choice;
    };

    /**
     * initializes.
     *
     * @param aJdbcTemplate    the a jdbc template
     * @param aPracticeService the practiceservice
     * @param aValidator       thevalidator
     * @param aDataSource      the a data source
     */
    public QuestionService(final JdbcTemplate aJdbcTemplate,
                           final PracticeService aPracticeService,
                           final Validator aValidator,
                           final DataSource aDataSource) {
        this.jdbcTemplate = aJdbcTemplate;
        this.practiceService = aPracticeService;
        this.validator = aValidator;
        this.dataSource = aDataSource;
    }

    /**
     * inserts data, (method overloading).
     *
     * @param practiceId the practice id
     * @param type       the type
     * @param question   the question
     * @return question optional
     */
    public Optional<Question> create(final Integer practiceId,
                                     final QuestionType type,
                                     final Question question) {
        return create(practiceId, null, type, question);
    }


    /**
     * inserts data.
     *
     * @param practiceId  the practice id
     * @param chapterPath the chapterPath
     * @param type        the type
     * @param question    the question
     * @return question optional
     */
    @Transactional
    public Optional<Question> create(final Integer practiceId,
                                     final String chapterPath,
                                     final QuestionType type,
                                     final Question question) {
        question.setType(type);
        Set<ConstraintViolation<Question>> violations =
                getViolations(question);
        if (violations.isEmpty()) {
            final SimpleJdbcInsert insert =
                    new SimpleJdbcInsert(dataSource)
                            .withTableName("questions")
                            .usingGeneratedKeyColumns("id")
                            .usingColumns("exam_id",
                                    "question", "chapter_path", "type",
                                    "answer");

            final Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("exam_id", practiceId);
            valueMap.put("question", question.getQuestion());
            valueMap.put("chapter_path", chapterPath);
            valueMap.put("type", type);
            valueMap.put("answer", question.getAnswer());

            final Number id = insert.executeAndReturnKey(valueMap);

            if ((question.getType().equals(QuestionType.CHOOSE_THE_BEST)
                    || question.getType().equals(QuestionType.MULTI_CHOICE))
                    && question.getChoices() != null) {
                final SimpleJdbcInsert insertQuestionChoice =
                        new SimpleJdbcInsert(dataSource)
                                .withTableName("question_choices")
                                .usingGeneratedKeyColumns("id")
                                .usingColumns("question_id",
                                        "value", "is_answer");

                question.getChoices().forEach(choice -> {
                    Map<String, Object> valueMapQuestionChoice =
                            new HashMap<>();
                    valueMapQuestionChoice.put("question_id", id);
                    valueMapQuestionChoice.put("value", choice.getValue());
                    valueMapQuestionChoice.put("is_answer",
                            choice.isAnswer() != null && choice.isAnswer());

                    insertQuestionChoice
                            .executeAndReturnKey(valueMapQuestionChoice);
                });

            }

            return read(id.intValue());
        } else {
            throw new ConstraintViolationException(violations);
        }

    }

    /**
     * creates a new question for a book.
     *
     * @param bookName     the bookName
     * @param questionType the questionType
     * @param question     the question
     * @param chapterPath  chapterPath
     * @return question optional
     */
    public Optional<Question> create(final String bookName,
                                     final QuestionType questionType,
                                     final Question question,
                                     final String chapterPath)
            throws JsonProcessingException {

        Practice practice = practiceService.getQuestionBank(bookName);

        return create(practice.getId(), chapterPath,
                questionType, question);

    }

    /**
     * List question choice list.
     *
     * @param isOwner          isOwner calling
     * @param questionId the question choice id
     * @return the list
     */
    private List<Choice> listQuestionChoice(final boolean isOwner,
                                            final Integer questionId) {
        final String query =
                "SELECT id,question_id,value,"
                        + (isOwner ? "is_answer" : "NULL")
                        + " AS is_answer"
                        + " FROM question_choices WHERE"
                        + " question_id = ?";
        return jdbcTemplate.query(query, rowMapperQuestionChoice,
                questionId);
    }

    /**
     * reads from question with given id.
     *
     * @param id the id
     * @return question optional
     */
    public Optional<Question> read(final Integer id) {
        final String query =
                "SELECT id,exam_id,question,chapter_path,type,answer FROM "
                        + "questions WHERE"
                        + " id = ?";
        try {

            Question question = jdbcTemplate
                    .queryForObject(query, new Object[]{id}, rowMapper);

            if ((question.getType().equals(QuestionType.CHOOSE_THE_BEST)
                    || question.getType().equals(QuestionType.MULTI_CHOICE))) {
                question.setChoices(
                        listQuestionChoice(true, question.getId()));
            }
            return Optional.of(question);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    /**
     * updates question with id for a book.
     *
     * @param bookName    the exam id
     * @param id          the id
     * @param type        the type
     * @param question    the question
     * @param chapterPath the chapterPath
     * @return question optional
     */
    @Transactional
    public Optional<Question> update(final String bookName,
                                     final QuestionType type,
                                     final Integer id,
                                     final Question question,
                                     final String chapterPath)
            throws JsonProcessingException {

        Practice practice = practiceService.getQuestionBank(bookName);

        return update(practice.getId(), type,
                id, question);

    }


    /**
     * updates question with id.
     *
     * @param practiceId the exam id
     * @param id         the id
     * @param type       the type
     * @param question   the question
     * @return question optional
     */
    public Optional<Question> update(final Integer practiceId,
                                     final QuestionType type,
                                     final Integer id,
                                     final Question question) {
        question.setType(type);
        Set<ConstraintViolation<Question>> violations =
                getViolations(question);
        if (violations.isEmpty()) {
            final String query =
                    "UPDATE questions SET exam_id = ?,"
                            + "question = ?, answer = ?"
                            + " WHERE id = ? AND type = ? AND exam_id = ?";
            final Integer updatedRows =
                    jdbcTemplate.update(query,
                            practiceId, question.getQuestion(),
                            question.getAnswer(), id, type.toString(),
                            practiceId);

            if ((type.equals(QuestionType.CHOOSE_THE_BEST)
                    || type.equals(QuestionType.MULTI_CHOICE))
                    && question.getChoices() != null) {


                final SimpleJdbcInsert insertQuestionChoice =
                        new SimpleJdbcInsert(dataSource)
                                .withTableName("question_choices")
                                .usingGeneratedKeyColumns("id")
                                .usingColumns("question_id",
                                        "value", "is_answer");

                List<Integer> availableIds = question.getChoices()
                        .stream()
                        .filter(choice -> choice.getId() != null)
                        .map(Choice::getId)
                        .collect(Collectors.toList());

                if (!availableIds.isEmpty()) {
                    final String deletequestionChoice =
                            "DELETE question_choices "
                                    + "WHERE question_id = ? AND id NOT IN ("
                                    + availableIds.stream()
                                    .map(aId -> "?")
                                    .collect(Collectors.joining(","))
                                    + ")";
                    availableIds.add(0, id);
                    jdbcTemplate.update(deletequestionChoice,
                             availableIds.toArray());
                }

                question.getChoices().forEach(choice -> {
                    if (choice.getId() == null) {
                        Map<String, Object> valueMapQuestionChoice =
                                new HashMap<>();
                        valueMapQuestionChoice.put("question_id", id);
                        valueMapQuestionChoice
                                .put("value", choice.getValue());
                        valueMapQuestionChoice
                                .put("is_answer",
                                        choice.isAnswer() != null
                                                && choice.isAnswer());

                        final Number insertedId = insertQuestionChoice
                                .executeAndReturnKey(valueMapQuestionChoice);


                    } else {

                        final String updatequestionChoice =
                                "UPDATE question_choices SET value = ?, "
                                        + "is_answer = ? "
                                        + "WHERE id = ?";

                        jdbcTemplate.update(updatequestionChoice,
                                choice.getValue(),
                                choice.isAnswer() != null && choice.isAnswer(),
                                choice.getId());
                    }


                });

            }
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
        String query = "DELETE FROM questions WHERE ID=?";

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

        Practice practice = this.practiceService
                .read(practiceId)
                .orElseThrow(IllegalArgumentException::new);

        boolean isOwner = practice.getOwner().equals(userName);

        final String query = "SELECT id,exam_id,question,type,"
                + (isOwner ? "answer" : "NULL")
                + " AS answer"
                + " FROM questions"
                + " where exam_id = ? order by id";
        List<Question> questions = jdbcTemplate.query(query, rowMapper,
                practiceId);
        if (!questions.isEmpty()) {
            questions.forEach(question -> {
                if ((question.getType().equals(QuestionType.CHOOSE_THE_BEST)
                        || question.getType()
                        .equals(QuestionType.MULTI_CHOICE))) {
                    question.setChoices(this
                            .listQuestionChoice(isOwner, question.getId()));
                }
            });
        }
        return questions;
    }

    /**
     * List questions of exam.
     *
     * @param userName    the user name
     * @param bookName    the practice id
     * @param chapterPath the chapterPath
     * @return quetions in given exam
     */
    public List<Question> list(final String userName,
                               final String bookName,
                               final String chapterPath)
            throws JsonProcessingException {

        Practice practice = practiceService.getQuestionBank(bookName);

        boolean isOwner = practice.getOwner().equals(userName);

        final String query = "SELECT id,exam_id,question,type,"
                + (isOwner ? "answer" : "NULL")
                + " AS answer"
                + " FROM questions"
                + " where exam_id = ? AND chapter_path = ? order by id";
        List<Question> questions = jdbcTemplate.query(query, rowMapper,
                practice.getId(), chapterPath);
        if (!questions.isEmpty()) {
            questions.forEach(question -> {
                if ((question.getType().equals(QuestionType.CHOOSE_THE_BEST)
                        || question.getType()
                        .equals(QuestionType.MULTI_CHOICE))) {
                    question.setChoices(this
                            .listQuestionChoice(isOwner, question.getId()));
                }
            });
        }
        return questions;
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

    /**
     * Validate Question.
     *
     * @param question
     * @return violations
     */
    private Set<ConstraintViolation<Question>> getViolations(final Question
                                                                     question) {
        Set<ConstraintViolation<Question>> violations = new HashSet<>(validator
                .validate(question));
        if (violations.isEmpty()) {
            final String messageTemplate = null;
            final Class<Question> rootBeanClass = Question.class;
            final Object rootBean = null;
            final Object leafBeanInstance = null;
            final Object value = null;
            final Path propertyPath = null;
            final ConstraintDescriptor<?> constraintDescriptor = null;
            final ElementType elementType = null;
            final Map<String, Object> messageParameters = new HashMap<>();
            final Map<String, Object> expressionVariables = new HashMap<>();
            if (question.getType().equals(QuestionType.MULTI_CHOICE)
                    || question.getType()
                    .equals(QuestionType.CHOOSE_THE_BEST)) {
                if (question.getChoices() == null
                        || question.getChoices().size() < 2) {
                    ConstraintViolation<Question> violation
                            = ConstraintViolationImpl.forBeanValidation(
                            messageTemplate, messageParameters,
                            expressionVariables,
                            "Minimun 2 choices",
                            rootBeanClass,
                            question, leafBeanInstance, value, propertyPath,
                            constraintDescriptor, elementType);
                    violations.add(violation);
                } else if (question.getAnswer() != null) {
                    ConstraintViolation<Question> violation
                            = ConstraintViolationImpl.forBeanValidation(
                            messageTemplate, messageParameters,
                            expressionVariables,
                            "Answer should be empty", rootBeanClass,
                            question, leafBeanInstance, value, propertyPath,
                            constraintDescriptor, elementType);
                    violations.add(violation);
                }
            } else {
                if (question.getAnswer() == null) {
                    ConstraintViolation<Question> violation
                            = ConstraintViolationImpl.forBeanValidation(
                            messageTemplate, messageParameters,
                            expressionVariables,
                            "Answer should not be empty",
                            rootBeanClass,
                            question, leafBeanInstance, value, propertyPath,
                            constraintDescriptor, elementType);
                    violations.add(violation);
                } else if (question.getChoices() != null) {
                    ConstraintViolation<Question> violation
                            = ConstraintViolationImpl.forBeanValidation(
                            messageTemplate, messageParameters,
                            expressionVariables,
                            "Choiced should not be aavailable",
                            rootBeanClass,
                            question, leafBeanInstance, value, propertyPath,
                            constraintDescriptor, elementType);
                    violations.add(violation);
                }
            }
        }
        return violations;
    }

    /**
     * deletes from database.
     *
     * @param id           the id
     * @param questionType the questionType
     * @return successflag boolean
     */
    @Transactional
    public Boolean deleteAQuestion(final int id,
                                   final QuestionType questionType) {

        deleteQuestionChoice(id);

        final String query =
                "DELETE FROM questions WHERE ID=? and type = ?";
        int updatedRow =
                jdbcTemplate.update(query, id, questionType.toString());
        return !(updatedRow == 0);

    }
}
