package com.example.demo.sql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import com.example.demo.sql.model.Database;
import com.example.demo.sql.model.Exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * logger to log the trace or error.
     */
    private Logger logger = LoggerFactory.getLogger(ExamServiceTest.class);

    @BeforeEach
    void before() {
        examService.delete();
    }

    @Test
    void testCreate() throws IOException {
        Exam exam = examService.create(getExam(), getScriptFiles()).get();
        assertEquals(EXAM1, exam.getName());
    }

    @Test
    void testUpdate() throws IOException {
        Exam exam = examService.create(getExam(), getScriptFiles()).get();
        exam.setName("Updated Name");
        exam.setDatabase(Database.MYSQL);
        Integer newExamId = exam.getId();
        exam = examService.update(newExamId, exam).get();
        assertEquals("Updated Name", exam.getName(), "Updated");
        assertEquals("mysql", exam.getDatabase().getValue(), "Updated");
    }

    @Test
    void testRead() throws IOException {
        Exam exam = examService.create(getExam(), getScriptFiles()).get();
        Integer newExamId = exam.getId();
        assertNotNull(examService.read(newExamId).get(), "Assert Created");
    }

    @Test
    void testDelete() {

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Exam exam = examService.create(getExam(), getScriptFiles()).get();
            Integer newExamId = exam.getId();
            examService.delete(newExamId);
            examService.read(newExamId).get();
        });
    }

    @Test
    void testList() throws IOException {
        examService.create(getExam(), getScriptFiles()).get();
        Exam exam2 = getExam();
        examService.create(exam2, getScriptFiles());
        assertEquals(2, examService.list(1, 2).size(), "Test Listing");
        assertEquals(1, examService.list(1, 1).size(), "Test Listing with restricted page");
    }

    Exam getExam() {
        Exam exam = new Exam();
        exam.setName(EXAM1);
        exam.setDatabase(Database.POSTGRES);
        return exam;
    }

    /**
     * Create Temporary SQL Files in temp folder. Return Files as array.
     * 
     * @return array of sript file
     */
    Path[] getScriptFiles() {
        Path[] files = new Path[1];
        String basePath = System.getProperty("java.io.tmpdir");
        Path createdTempFolder = null;
        Path tempFile = null;
        try {
            createdTempFolder = Files.createTempDirectory(Paths.get(basePath), "temp");
            tempFile = Files.createTempFile(createdTempFolder, "temp", ".sql");
            Files.write(tempFile, "Sample Data".getBytes());
        } catch (IOException e) {
            logger.error("Error in creating the file : " + e.getMessage());
        }
        files[0] = tempFile;
        return files;
    }
}
