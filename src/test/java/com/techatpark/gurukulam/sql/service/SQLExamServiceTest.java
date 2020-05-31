package com.techatpark.gurukulam.sql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamServiceTest {
    /**
     * instance used for test cases.
     */
    private static final String EXAM1 = "Exam 1";
    /**
     * Service instance to be tested.
     */
    @Autowired
    private ExamService examService;

    @BeforeEach
    void before() {
        examService.delete();
    }

    @Test
    void testCreate() throws IOException {
        Exam examToBeCrated = getExam();
        Exam createdExam = examService.create(examToBeCrated, getScriptFiles(examToBeCrated)).get();
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
        Exam exam = examService.create(examToBeCrated, getScriptFiles(examToBeCrated)).get();
        exam.setName("Updated Name");
        exam.setDatabase(Database.POSTGRES);
        Integer newExamId = exam.getId();
        exam = examService.update(newExamId, exam).get();
        assertEquals("Updated Name", exam.getName(), "Updated");
        assertEquals(Database.POSTGRES, exam.getDatabase(), "Updated");
    }

    @Test
    void testRead() throws IOException {
        Exam examToBeCrated = getExam();
        Exam exam = examService.create(examToBeCrated, getScriptFiles(examToBeCrated)).get();
        Integer newExamId = exam.getId();
        Assertions.assertNotNull(examService.read(newExamId).get(), "Exam Created");
    }

    @Test
    void testDelete() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Exam examToBeCrated = getExam();
            Exam exam = examService.create(examToBeCrated, getScriptFiles(examToBeCrated)).get();
            Integer newExamId = exam.getId();
            examService.delete(newExamId);
            examService.read(newExamId).get();
        });
    }

    @Test
    void testList() throws IOException {
        Exam examToBeCrated = getExam();
        examService.create(examToBeCrated, getScriptFiles(examToBeCrated)).get();
        Exam examToBeCrated2 = getExam();
        examService.create(examToBeCrated2, getScriptFiles(examToBeCrated2));
        assertEquals(2, examService.list(1, 2).size(), "Test Listing");
        assertEquals(1, examService.list(1, 1).size(), "Test Listing with restricted page");
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
        Path[] scripts = getScriptFiles(examToBeCrated);
        assertEquals(2, scripts.length, "All (2) script files loaded");
    }

    /**
     * Create Temporary SQL Files in temp folder. Return Files as array.
     * @param exam
     * @return array of sript file
     */
    Path[] getScriptFiles(final Exam exam) {
        Path[] scripts = new Path[2];
        File file = new File("src/test/resources/" + exam.getDatabase().getValue() + "/scripts");
        return Arrays.asList(file.listFiles()).stream().map(script -> script.toPath()).collect(Collectors.toList()).toArray(scripts);
    }
}
