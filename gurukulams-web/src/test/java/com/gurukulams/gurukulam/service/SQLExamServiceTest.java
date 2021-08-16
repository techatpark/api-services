package com.gurukulams.gurukulam.service;

import com.gurukulams.gurukulam.model.Database;
import com.gurukulams.gurukulam.model.sql.SqlPractice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Sql exam service test.
 */
@SpringBootTest
class SQLExamServiceTest {
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
                sqlExamService.create("sql", "user", examToBeCrated).get();
        assertEquals(EXAM1, createdExam.getName());
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
                sqlExamService.create("sql", "user", examToBeCrated).get();
        exam.setName("Updated Name");
        exam.setDatabase(Database.POSTGRES);
        final Integer newExamId = exam.getId();
        exam = sqlExamService.update(newExamId, exam).get();
        assertEquals("Updated Name", exam.getName(), "Updated");
        assertEquals(Database.POSTGRES, exam.getDatabase(), "Updated");
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
                sqlExamService.create("sql", "user", examToBeCrated).get();
        final Integer newExamId = exam.getId();
        Assertions.assertNotNull(sqlExamService.read(newExamId).get(),
                "Exam Created");
    }

    /**
     * Test delete.
     */
    @Test
    void testDelete() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            final SqlPractice examToBeCrated = getExam();
            final SqlPractice exam =
                    sqlExamService.create("sql", "user", examToBeCrated).get();
            final Integer newExamId = exam.getId();
            sqlExamService.delete(newExamId);
            sqlExamService.read(newExamId).get();
        });
    }

    /**
     * Test list.
     *
     * @throws IOException the io exception
     */
    @Test
    void testList() throws IOException {
        final SqlPractice examToBeCrated = getExam();
        sqlExamService.create("sql", "user", examToBeCrated).get();
        final SqlPractice examToBeCrated2 = getExam();
        sqlExamService.create("sql", "user", examToBeCrated2);
        assertEquals(2,
                sqlExamService.page("sql", PageRequest.of(0, 2)).getContent()
                        .size()
                , "Test Listing");
        assertEquals(1,
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
        exam.setName(EXAM1);
        exam.setDatabase(Database.POSTGRES);
        exam.setScript(TestUtil.getScript(exam));
        exam.setDescription("description");
        return exam;
    }


}
