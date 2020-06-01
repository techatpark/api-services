package com.techatpark.gurukulam.sql.service.connector.postgress;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostgressDatabaseConnectorTest {

    @Autowired
    private PostgressDatabaseConnector postgressDatabaseConnector;

    @Test
    public void testVerify() {

        boolean result = postgressDatabaseConnector.verify(getExam(), getQuestion(), sqlAnswer)
        assertTrue(result);
    }

    Exam getExam() {
        Exam exam = new Exam();
        exam.setName(EXAM1);
        exam.setDatabase(Database.POSTGRES);
        return exam;
    }
}
