package com.example.demo.sql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

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
    void testCreate() {
        Exam exam = examService.create(getExam(), getScriptFiles()).get();
        assertEquals(EXAM1, exam.getName());
    }

    @Test
    void testUpdate() {
        Exam exam = examService.create(getExam(), getScriptFiles()).get();
        exam.setName("Updated Name");
        Integer newExamId = exam.getId();
        exam = examService.update(newExamId, exam).get();
        assertEquals("Updated Name", exam.getName(), "Updated");
    }

    @Test
    void testRead() {
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
    void testList() {
        examService.create(getExam(), getScriptFiles()).get();
        Exam exam2 = getExam();
        examService.create(exam2, null);
        assertEquals(2, examService.list(1, 2).size(), "Test Listing");
        assertEquals(1, examService.list(1, 1).size(), "Test Listing with restricted page");
    }

    Exam getExam() {
        Exam exam = new Exam();
        exam.setName(EXAM1);
        return exam;
    }

    /**
     * Create Temporary SQL Files in temp folder. Return Files as array.
     * 
     * @return array of sript file
     */
    File[] getScriptFiles() {
        File[] files = new File[1];

        String basePath = System.getProperty("java.io.tmpdir");
        String file = basePath + File.separator + "ddl.sql";
        File scriptFile = new File(file);
        try {
            if (scriptFile.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                writer.write("Test data");
                writer.close();
            }
        } catch (IOException e) {
            logger.error("Error in creating the file : " + e.getMessage());
        }
        files[0] = scriptFile;
        return files;
    }
}
