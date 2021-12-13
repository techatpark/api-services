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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LearnerServiceTest {
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
    final Learner learner = learnerService.create("mani",
                                                      anLearner());
        assertTrue(learnerService.read("mani",learner.id()).isPresent(),"Created Learner");

    }

    @Test
    void read() {
        final Learner learner = learnerService.create("Manikanta",
                                     anLearner());
        Assertions.assertTrue(learnerService.read("Manikanta",
                learner.id()).isPresent(), "Learner created");
    }

    @Test
    void update() {
        final Learner learner=learnerService.create("Manikanta",
                                                      anLearner());
        final Long newLearnerId = learner.id();
        Learner newLearner=new Learner(null, "maniLearner","abcd123@gmail.com",
                           "An Learner", null,null);
        Learner updatedLearner=learnerService.update(newLearnerId,
                                    "Mani",newLearner);
        assertEquals("maniLearner", updatedLearner.name(), "updated");
               Assertions.assertThrows(IllegalArgumentException.class, () -> {
                   learnerService.update(10000L, "Mani", newLearner);
        });
    }

    @Test
    void delete() {
        final Learner learner=learnerService.create("Manikanta",
                                                 anLearner());
        learnerService.delete("mani",learner.id());
        assertFalse(learnerService.read("mani",learner.id()).isPresent(),"Deleted Learner");
    }

    @Test
    void list() {
        final Learner learner=learnerService.create("Manikanta",
                                                        anLearner());
        Learner newLearner=new Learner(null, "tom","abcd123@gmail.com",
                         "An Learner", null, null);
        learnerService.create("Manikanta", newLearner);
        List<Learner> listOfLearner = learnerService.list("Manikanta");
        Assertions.assertEquals(2, listOfLearner.size());
    }

    Learner anLearner() {
        Learner learner=new Learner(null,"Manikanta",
                "abcd123@gmail.com",
                "An Learner",
                null,null);
        return learner;
    }
}
