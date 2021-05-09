package com.techatpark.gurukulam.service.connector.postgress;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techatpark.gurukulam.model.Database;
import com.techatpark.gurukulam.model.Practice;
import com.techatpark.gurukulam.model.Question;
import com.techatpark.gurukulam.model.sql.SqlPractice;
import com.techatpark.gurukulam.service.PracticeService;
import com.techatpark.gurukulam.service.QuestionService;
import com.techatpark.gurukulam.service.TestUtil;
import com.techatpark.gurukulam.service.connector.DatabaseConnector;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
     * @throws IOException
     */
    @BeforeEach
    void before() throws IOException {
        questionService.delete();
        sqlExamService.delete("sql");
    }

    /**
     *
     */
    //@Test
    public void testVerify() throws JsonProcessingException {
        SqlPractice exam = createAndGetExam();
        Question question = createAndGQuestion(exam);
        boolean result = postgressDatabaseConnector.verify(exam, question, getAnswer());
        assertTrue(result);
    }

    /**
     *
     */
    //@Test
    public void testVerifywrongAnswer() throws JsonProcessingException {
        SqlPractice exam = createAndGetExam();
        Question question = createAndGQuestion(exam);
        boolean result = postgressDatabaseConnector.verify(exam, question, "select * from abc");
        assertFalse(result);
    }

    /**
     * @return exam
     */
    SqlPractice getExam() {
        SqlPractice exam = new SqlPractice();
        exam.setName("Exam_1");
        exam.setDatabase(Database.POSTGRES);
        exam.setScript(TestUtil.getScript(exam));
        exam.setDescription("description");
        return exam;
    }

    /**
     * @return qustion
     */
    Question getQuestion() {
        final Question question = new Question();
        question.setQuestion(QUERY1);
        question.setAnswer(ANSWER1);
        return question;
    }

    /**
     * @return answer String
     */
    String getAnswer() {
        return "SELECT * FROM exams";
    }

    /**
     * @return Exam
     */
    SqlPractice createAndGetExam() throws JsonProcessingException {
        SqlPractice exam = sqlExamService.create("sql" ,getExam()).get();
        return exam;
    }

    /**
     * @param exam
     * @return question
     */
    Question createAndGQuestion(final Practice exam) {
        final Question question = questionService.create(exam.getId(), getQuestion()).get();
        return question;
    }

}
