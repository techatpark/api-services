package com.techatpark.gurukulam.sql.service.connector.postgress;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Practice;
import com.techatpark.gurukulam.sql.model.Question;
import com.techatpark.gurukulam.sql.service.QuestionService;
import com.techatpark.gurukulam.sql.service.SQLPracticeService;
import com.techatpark.gurukulam.sql.service.TestUtil;
import com.techatpark.gurukulam.sql.service.connector.DatabaseConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    private SQLPracticeService sqlExamService;

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
        sqlExamService.delete();
    }

    /**
     *
     */
    //@Test
    public void testVerify() {
        Practice exam = createAndGetExam();
        Question question = createAndGQuestion(exam);
        boolean result = postgressDatabaseConnector.verify(exam, question, getAnswer());
        assertTrue(result);
    }

    /**
     *
     */
    //@Test
    public void testVerifywrongAnswer() {
        Practice exam = createAndGetExam();
        Question question = createAndGQuestion(exam);
        boolean result = postgressDatabaseConnector.verify(exam, question, "select * from abc");
        assertFalse(result);
    }

    /**
     * @return exam
     */
    Practice getExam() {
        Practice exam = new Practice();
        exam.setName("Exam_1");
        exam.setDatabase(Database.POSTGRES);
        exam.setScript(TestUtil.getScript(exam));
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
    Practice createAndGetExam() {
        Practice exam = sqlExamService.create(getExam()).get();
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
