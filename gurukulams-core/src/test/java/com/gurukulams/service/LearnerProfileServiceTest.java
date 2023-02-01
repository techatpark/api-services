package com.gurukulams.service;

import com.gurukulams.core.model.AuthProvider;
import com.gurukulams.core.model.Learner;
import com.gurukulams.core.model.LearnerProfile;
import com.gurukulams.core.service.LearnerProfileService;
import com.gurukulams.core.service.LearnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class LearnerProfileServiceTest {

    @Autowired
    private LearnerProfileService learnerProfileService;
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
        learnerProfileService.deleteAll();
        learnerService.deleteAll();
        learnerProfileService.deleteAllHandle();
    }

    @Test
    void create() {
        final LearnerProfile learner = learnerProfileService.create("mani",
                anLearner());
        Assertions.assertTrue(learnerProfileService.read("mani", learner.id()).isPresent(), "Created Learner");

    }

    @Test
    void delete() {
        final LearnerProfile learner = learnerProfileService.create("Manikanta",
                anLearner());
        learnerProfileService.delete("mani", learner.id());
        Assertions.assertFalse(learnerProfileService.readByUUID("Manikanta", learner.learnerId()).isPresent(), "Deleted Learner");
    }


    @Test
    void list() {
        LearnerProfile learner = learnerProfileService.create("Manikanta",
                anLearner());
        LearnerProfile learner1 = learnerProfileService.create("Manikanta",
                        anLearner1());
        List<LearnerProfile> listOfLearner = learnerProfileService.list("Manikanta");
        Assertions.assertEquals(2, listOfLearner.size());
    }

    @Test
    void update() {
        final LearnerProfile learner = learnerProfileService.create("Manikanta",
                anLearner());
        final String newLearnerId = learner.id();

        LearnerProfile updatedLearner = learnerProfileService.update(newLearnerId,
                "Mani", anLearner1());
        Assertions.assertEquals("Hari", updatedLearner.firstName(), "updated");
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            learnerProfileService.update(UUID.randomUUID(), "Mani", anLearner1());
//        });
    }

    LearnerProfile anLearner() {
        Learner learner = new Learner(null, "jhjhf@gmail",
                "An Description",
                "Image Url", AuthProvider.local, null, null,
                null, null);
        Learner l = learnerService.create("Manikanta",
                learner);

        LearnerProfile learnerProfile = new LearnerProfile("HariUserID", l.id(),
                "First Name",
                "Last Name");
        return learnerProfile;
    }
    LearnerProfile anLearner1() {
        Learner learner = new Learner(null, "jhjsvhjcksdvsd@gmail",
                "An Description",
                "Image Url", AuthProvider.local, null, null,
                null, null);
        Learner l = learnerService.create("Manikanta",
                learner);
        LearnerProfile learnerProfile = new LearnerProfile("HariPriyaUserID", l.id(),
                "Hari",
                "Priya");
        return learnerProfile;
    }

}
