package com.gurukulams.core.service;

import com.gurukulams.core.model.Learner;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import java.io.IOException;
import java.sql.SQLException;
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
    final Learner learner = learnerService.create(
                                                      anLearner());
        assertTrue(learnerService.read(learner.id()).isPresent(),"Created Learner");

    }
//
////    false condition check for email-- unique key
//    @Test
//    void createWithSameEmailId() {
//        final Learner learner = learnerService.create("mani",
//                anLearner());
////        final Learner learner1 = learnerService.create("mani",
////                anLearner());
//        Exception exception = assertThrows(SQLException.class,
//                () -> learnerService.create("mani",
//                anLearner()));
//        assertEquals("Exception Message", exception.getMessage());
//    }

    @Test
    void read() {
        final Learner learner = learnerService.create(
                                     anLearner());
        Assertions.assertTrue(learnerService.read(
                learner.id()).isPresent(), "Learner Found");
    }

    @Test
    void readByEmail() {
        final Learner learner = learnerService.create(
                anLearner());
        Assertions.assertTrue(learnerService.readByEmail(learner.email()).isPresent(),
                                                   "Learner Found");
    }

    @Test
    void update() {
        final Learner learner=learnerService.create(
                                                      anLearner());
        final Long newLearnerId = learner.id();
        Learner newLearner=new Learner(null, "maniLearner","abcd123@gmail.com",
                           "http://learnerimage.jpg","GYTL5@1654",
                      "google","121",
                      "An Learner", null,null);
        Learner updatedLearner=learnerService.update(newLearnerId,
                                    newLearner);
        assertEquals("maniLearner", updatedLearner.name(), "updated");
               Assertions.assertThrows(IllegalArgumentException.class, () -> {
                   learnerService.update(10000L, newLearner);
        });
    }

    @Test
    void delete() {
        final Learner learner=learnerService.create(
                                                 anLearner());
        learnerService.delete("mani",learner.id());
        assertFalse(learnerService.read(learner.id()).isPresent(),"Deleted Learner");
    }

    @Test
    void list() {
        final Learner learner=learnerService.create(
                                                        anLearner());
        Learner newLearner=new Learner(null, "tom","abcdnew@gmail.com",
                "http://learnerimage.jpg","GYTL5@1654",
                    "google","121",
                         "An Learner", null, null);
        learnerService.create( newLearner);
        List<Learner> listOfLearner = learnerService.list("Manikanta");
        Assertions.assertEquals(2, listOfLearner.size());
    }

    Learner anLearner() {
        Learner learner=new Learner(null,"Manikanta",
                "abcd123@gmail.com",
                "http://learnerimage.jpg",
                "GYTL5@1654",
                "google",
                "121",
                "An Learner",
                null,null);
        return learner;
    }
}
