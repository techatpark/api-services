package com.techatpark.gurukulam.sql.service.connector.postgress;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Exam;
import com.techatpark.gurukulam.sql.model.Question;
import com.techatpark.gurukulam.sql.service.QuestionService;
import com.techatpark.gurukulam.sql.service.SQLExamService;
import com.techatpark.gurukulam.sql.service.TestUtil;
import com.techatpark.gurukulam.sql.service.connector.DatabaseConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
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
    private SQLExamService sqlExamService;

    /**
     * Service instance to be tested.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * 
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
    @Test
    public void testVerify() {
        Exam exam = createAndGetExam();
        Question question = createAndGQuestion(exam);
        boolean result = postgressDatabaseConnector.verify(exam, question, getAnswer());
        assertTrue(result);
    }

    /**
     * 
     */
    //@Test
    public void testVerifywrongAnswer() {
        Exam exam = createAndGetExam();
        Question question = createAndGQuestion(exam);
        boolean result = postgressDatabaseConnector.verify(exam, question, "select * from abc");
        assertFalse(result);
    }

    /**
     * 
     * @return exam
     */
    Exam getExam() {
        Exam exam = new Exam();
        exam.setName("Exam_1");
        exam.setDatabase(Database.POSTGRES);
        return exam;
    }

    /**
     * 
     * @return qustion
     */
    Question getQuestion() {
        final Question question = new Question();
        question.setQuestion(QUERY1);
        question.setAnswer(ANSWER1);
        return question;
    }

    /**
     * 
     * @return answer String
     */
    String getAnswer() {
        return "SELECT * FROM exams";
    }

    /**
     * 
     * @return Exam
     */
    Exam createAndGetExam() {
        Exam examToBeCrated = getExam();
        Exam exam = null;
        try {
            exam = sqlExamService.create(examToBeCrated, TestUtil.getScriptFiles(examToBeCrated)).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exam;
    }

    /**
     * 
     * @param exam
     * @return question
     */
    Question createAndGQuestion(final Exam exam) {
        final Question question = questionService.create(exam.getId(), getQuestion()).get();
        return question;
    }

}
