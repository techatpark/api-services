package com.gurukulams.core.service.connector.postgress;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Database;
import com.gurukulams.core.model.Practice;
import com.gurukulams.core.model.Question;
import com.gurukulams.core.model.QuestionType;
import com.gurukulams.core.model.sql.SqlPractice;
import com.gurukulams.core.service.PracticeService;
import com.gurukulams.core.service.QuestionService;
import com.gurukulams.core.service.TestUtil;
import com.gurukulams.core.service.connector.DatabaseConnector;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Postgress database connector test.
 */
//@SpringBootTest
public class PostgressDatabaseConnectorTest {
    /**
     * variable to be used for testing.
     */
    private static final String QUERY1 = "Query for all tables.";

    /**
     * variable to be used for testing.
     */
    private static final String ANSWER1 = "SELECT * FROM exams";

    /**
     * variable to be used for testing.
     */
    private static final String TYPE = "Multiline";

    /**
     * Service instance to be tested.
     */
    @Autowired
    private DatabaseConnector postgressDatabaseConnector;

    /**
     * Service instance to be tested.
     */
    @Autowired
    private PracticeService sqlExamService;

    /**
     * Service instance to be tested.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Before.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void before() throws IOException {
        questionService.delete();
        sqlExamService.delete("sql");
    }

    /**
     * Test verify.
     *
     * @throws JsonProcessingException the json processing exception
     */
//@Test
    public void testVerify() throws JsonProcessingException {
        final SqlPractice exam = createAndGetExam();
        final Question question = createAndGQuestion(exam);
        final boolean result =
                postgressDatabaseConnector.verify(exam, question, getAnswer());
        assertTrue(result);
    }

    /**
     * Test verifywrong answer.
     *
     * @throws JsonProcessingException the json processing exception
     */
//@Test
    public void testVerifywrongAnswer() throws JsonProcessingException {
        final SqlPractice exam = createAndGetExam();
        final Question question = createAndGQuestion(exam);
        final boolean result = postgressDatabaseConnector
                .verify(exam, question, "select * from abc");
        assertFalse(result);
    }

    /**
     * Gets exam.
     *
     * @return exam exam
     */
    SqlPractice getExam() {
        final SqlPractice exam = new SqlPractice();
        exam.setName("Exam_1");
        exam.setDatabase(Database.H2);
        exam.setScript(TestUtil.getScript(exam));
        exam.setDescription("description");
        return exam;
    }

    /**
     * Gets question.
     *
     * @return qustion question
     */
    Question getQuestion() {
        final Question question = new Question();
        question.setQuestion(QUERY1);
        question.setAnswer(ANSWER1);
        return question;
    }

    /**
     * Gets answer.
     *
     * @return answer String
     */
    String getAnswer() {
        return "SELECT * FROM exams";
    }

    /**
     * Create and get exam sql practice.
     *
     * @return Exam sql practice
     * @throws JsonProcessingException the json processing exception
     */
    SqlPractice createAndGetExam() throws JsonProcessingException {
        final SqlPractice exam =
                sqlExamService.create("sql", "user", getExam()).get();
        return exam;
    }

    /**
     * Create and g question question.
     *
     * @param exam the exam
     * @return question question
     */
    Question createAndGQuestion(final Practice exam) {
        final Question question = questionService.create(exam.getId(),
                QuestionType.MULTI_LINE, getQuestion(),"user").get();
        return question;
    }

}