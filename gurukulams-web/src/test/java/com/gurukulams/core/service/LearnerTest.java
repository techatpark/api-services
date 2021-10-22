package com.gurukulams.core.service;

import com.gurukulams.core.model.Learner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

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
                newLearnerId), "Learner created");
        Assertions.assertNull(learnerService.read("Manikanta",
                10000L), "Invalid learner unavailable");
    }

    @Test
    void update() {
        final Learner learner=learnerService.create("Manikanta",
                                                      anLearner());
        final Long newLearnerId = learner.id();
        Learner newLearner=new Learner(null, "maniLearner", "An Learner",
                null,null,null,null);
        Learner updatedLearner=learnerService.update(newLearnerId,
                                    "Mani",newLearner);
        assertEquals("maniLearner", updatedLearner.title(), "updated");
               Assertions.assertThrows(IllegalArgumentException.class, () -> {
                   learnerService.update(10000L, "Mani", newLearner);
        });
    }

    @Test
    void delete() {
        final Learner learner=learnerService.create("Manikanta",
                                                 anLearner());
        final Long newLearnerId=learner.id();
        Assertions.assertTrue(learnerService.delete("Manikanta",
                                                    newLearnerId));
    }

    @Test
    void list() {
        final Learner learner=learnerService.create("Manikanta",
                                                        anLearner());
        Learner newLearner=new Learner(null, "tom", "An Learner",
                null, null, null, null);
        learnerService.create("Manikanta", newLearner);
        List<Learner> listOfLearner = learnerService.list("Manikanta");
        Assertions.assertEquals(2, listOfLearner.size());
    }

    Learner anLearner() {
        Learner learner=new Learner(null,"Manikanta",
                "An Description", null,null,
                null,null);
        return learner;
    }
}
