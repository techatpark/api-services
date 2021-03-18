package com.techatpark.gurukulam.sql.service;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Exam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private SQLExamService sqlExamService;

    @BeforeEach
    void before() {
        sqlExamService.delete();
    }

    @Test
    void testCreate() throws IOException {
        Exam examToBeCrated = getExam();
        Exam createdExam = sqlExamService.create(examToBeCrated, TestUtil.getScriptFiles(examToBeCrated)).get();
        assertEquals(EXAM1, createdExam.getName());
    }

    @Test
    void testCreateQuesion() throws IOException {

    }

    @Test
    void testAnswer() throws IOException {

    }

    @Test
    void testUpdate() throws IOException {
        Exam examToBeCrated = getExam();
        Exam exam = sqlExamService.create(examToBeCrated, TestUtil.getScriptFiles(examToBeCrated)).get();
        exam.setName("Updated Name");
        exam.setDatabase(Database.POSTGRES);
        Integer newExamId = exam.getId();
        exam = sqlExamService.update(newExamId, exam).get();
        assertEquals("Updated Name", exam.getName(), "Updated");
        assertEquals(Database.POSTGRES, exam.getDatabase(), "Updated");
    }

    @Test
    void testRead() throws IOException {
        Exam examToBeCrated = getExam();
        Exam exam = sqlExamService.create(examToBeCrated, TestUtil.getScriptFiles(examToBeCrated)).get();
        Integer newExamId = exam.getId();
        Assertions.assertNotNull(sqlExamService.read(newExamId).get(), "Exam Created");
    }

    @Test
    void testDelete() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Exam examToBeCrated = getExam();
            Exam exam = sqlExamService.create(examToBeCrated, TestUtil.getScriptFiles(examToBeCrated)).get();
            Integer newExamId = exam.getId();
            sqlExamService.delete(newExamId);
            sqlExamService.read(newExamId).get();
        });
    }

    @Test
    void testList() throws IOException {
        Exam examToBeCrated = getExam();
        sqlExamService.create(examToBeCrated, TestUtil.getScriptFiles(examToBeCrated)).get();
        Exam examToBeCrated2 = getExam();
        sqlExamService.create(examToBeCrated2, TestUtil.getScriptFiles(examToBeCrated2));
        assertEquals(2, sqlExamService.list(PageRequest.of(1, 2)).getContent().size(), "Test Listing");
        assertEquals(1, sqlExamService.list(PageRequest.of(1, 1)).getContent().size(), "Test Listing with restricted page");
    }

    Exam getExam() {
        Exam exam = new Exam();
        exam.setName(EXAM1);
        exam.setDatabase(Database.POSTGRES);
        return exam;
    }

    @Test
    void testLoadScripts() {
        Exam examToBeCrated = getExam();
        InputStream[] scripts = TestUtil.getScriptFiles(examToBeCrated);
        assertEquals(2, scripts.length, "All (2) script files loaded");
    }


}
