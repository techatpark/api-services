package com.gurukulams.core.service;

import com.gurukulams.core.model.Learner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class LearnerTest {
    @Autowired
    private LearnerService learnerService;

    @BeforeEach
    void before() throws IOException {
        cleanup();
    }

    @AfterEach
    void after() throws IOException {
        cleanup();
    }

    private void cleanup() {
        learnerService.deleteAll();
    }

    private void cleanUp() {
        learnerService.deleteAll();
    }

    @Test
    void create() {
    final Learner learner = learnerService.create("Manikanta",
                                                      anLearner());
    assertEquals("Manikanta", learner.title(),
                                   "created successfully");
    }

    @Test
    void read() {
        final Learner learner = learnerService.create("Manikanta",
                                     anLearner());
        final Long newLearnerId = learner.id();
        Assertions.assertNotNull(learnerService.read("Manikanta",
                newLearnerId), "Assert created");
    }

    Learner anLearner() {
        Learner learner=new Learner(null,"Manikanta",
                "An Description", null,null,
                null,null);
        return learner;
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void list() {
    }
}
