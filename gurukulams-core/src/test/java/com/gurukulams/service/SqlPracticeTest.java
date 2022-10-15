package com.gurukulams.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Database;
import com.gurukulams.core.model.sql.SqlPractice;
import com.gurukulams.core.service.PracticeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;

/**
 * The type Sql exam service test.
 */
@SpringBootTest
class SqlPracticeTest {
    /**
     * instance used for test cases.
     */
    private static final String EXAM1 = "Exam 1";
    /**
     * Service instance to be tested.
     */
    @Autowired
    private PracticeService sqlExamService;

    /**
     * Before each.
     */
    @BeforeEach
    void beforeEach() {
        cleanUp();
    }

    /**
     * After each.
     */
    @AfterEach
    void afterEach() {
        cleanUp();
    }

    /**
     * Clean up.
     */
    void cleanUp() {
        sqlExamService.delete("sql");
    }


    /**
     * Test create.
     *
     * @throws IOException the io exception
     */
    @Test
    void testCreate() throws IOException {
        final SqlPractice examToBeCrated = getExam();
        final SqlPractice createdExam =
                sqlExamService.create("sql", "user", null, examToBeCrated).get();
        Assertions.assertEquals(EXAM1, createdExam.getTitle());
    }


    /**
     * Test update.
     *
     * @throws IOException the io exception
     */
    @Test
    void testUpdate() throws IOException {
        final SqlPractice examToBeCrated = getExam();
        SqlPractice exam =
                sqlExamService.create("sql", "user", null,examToBeCrated).get();
        exam.setTitle("Updated Name");
        exam.setDatabase(Database.H2);
        final Integer newExamId = exam.getId();
        exam = sqlExamService.update(newExamId, null, exam).get();
        Assertions.assertEquals("Updated Name", exam.getTitle(), "Updated");
        Assertions.assertEquals(Database.H2, exam.getDatabase(), "Updated");
    }

    /**
     * Test read.
     *
     * @throws IOException the io exception
     */
    @Test
    void testRead() throws IOException {
        final SqlPractice examToBeCrated = getExam();
        final SqlPractice exam =
                sqlExamService.create("sql", "user", null, examToBeCrated).get();
        final Integer newExamId = exam.getId();
        Assertions.assertTrue(sqlExamService.read(newExamId, null).isPresent(),
                "Exam Created");
    }

    /**
     * Test delete.
     */
    @Test
    void testDelete() throws JsonProcessingException {

            final SqlPractice examToBeCrated = getExam();
            final SqlPractice exam =
                    sqlExamService.create("sql", "user",null, examToBeCrated).get();
            final Integer newExamId = exam.getId();
            sqlExamService.delete(newExamId);
        Assertions.assertFalse(sqlExamService.read(newExamId, null).isPresent(),
                "Exam Created");

    }

    /**
     * Test list.
     *
     * @throws IOException the io exception
     */
    @Test
    void testList() throws IOException {
        final SqlPractice examToBeCrated = getExam();
        sqlExamService.create("sql", "user", null,examToBeCrated).get();
        final SqlPractice examToBeCrated2 = getExam();
        sqlExamService.create("sql", "user", null, examToBeCrated2);
        Assertions.assertEquals(2,
                sqlExamService.page("sql", PageRequest.of(0, 2)).getContent()
                        .size()
                , "Test Listing");
        Assertions.assertEquals(1,
                sqlExamService.page("sql", PageRequest.of(0, 1)).getContent()
                        .size(), "Test Listing with restricted page");
    }

    /**
     * Gets exam.
     *
     * @return the exam
     */
    SqlPractice getExam() {
        final SqlPractice exam = new SqlPractice();
        exam.setTitle(EXAM1);
        exam.setDatabase(Database.H2);
        exam.setScript(TestUtil.getScript(exam));
        exam.setDescription("description");
        return exam;
    }


}
