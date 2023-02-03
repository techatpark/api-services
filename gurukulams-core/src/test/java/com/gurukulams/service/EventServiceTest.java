package com.gurukulams.service;

import com.gurukulams.core.model.AuthProvider;
import com.gurukulams.core.model.Event;
import com.gurukulams.core.model.Learner;
import com.gurukulams.core.service.EventService;
import com.gurukulams.core.service.LearnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class EventServiceTest {

    public static final String STATE_BOARD_IN_ENGLISH = "State Board";
    public static final String STATE_BOARD_DESCRIPTION_IN_ENGLISH = "State Board Description";

    @Autowired
    private EventService eventService;

    @Autowired
    private LearnerService learnerService;

    /**
     * Before.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void before() throws IOException {
        cleanUp();
    }

    /**
     * After.
     */
    @AfterEach
    void after() {
        cleanUp();
    }

    private void cleanUp() {
        eventService.deleteAll();
    }

    @Test
    void create() {
        final Event event = eventService.create("mani", null,
                anEvent());
        Assertions.assertTrue(eventService.read("mani", null, event.id()).isPresent(),
                "Created Event");
    }

    @Test
    void read() {
        final Event event = eventService.create("mani", null,
                anEvent());
        final UUID newEventId = event.id();
        Assertions.assertTrue(eventService.read("mani", null, newEventId).isPresent(),
                "Event Created");
    }

    @Test
    void update() {
        LocalDateTime date =
                LocalDateTime.of(2023, Month.MARCH, 10, 0, 0);
        final Event event = eventService.create("mani", null,
                anEvent());
        final UUID newEventId = event.id();
        Event newEvent = new Event(null, "Event", "A " +
                "Event", date, null, "tom", null, null);
        Event updatedBoard = eventService
                .update(newEventId, "mani", null, newEvent);
        Assertions.assertEquals("Event", updatedBoard.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            eventService
                    .update(UUID.randomUUID(), "mani", null, newEvent);
        });
    }

    @Test
    void delete() {

        final Event event = eventService.create("mani", null,
                anEvent());
        eventService.delete("mani", event.id());
        Assertions.assertFalse(eventService.read("mani", null, event.id()).isPresent(),
                "Deleted Event");

    }

    @Test
    void list() {
        LocalDateTime date =
                LocalDateTime.of(2023, Month.MARCH, 10, 0, 0);
        final Event event = eventService.create("mani", null,
                anEvent());
        Event newEvent = new Event(null, "Event New", "A " +
                "Event", date, null, "tom", null, null);
        eventService.create("mani", null,
                newEvent);
        List<Event> listofEvents = eventService.list("manikanta", null);
        Assertions.assertEquals(2, listofEvents.size());

    }

    @Test
    void registerForEvent(){
        final Learner learner = learnerService.create("mani",
                anLearner());

        final Event event = eventService.create("mani", null,
                anEvent());

        Assertions.assertTrue(eventService.register(event.id(), learner.email()));
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    Event anEvent() {
                LocalDateTime date =
                LocalDateTime.of(2023, Month.MARCH, 10, 0, 0);
        Event event = new Event(null, STATE_BOARD_IN_ENGLISH,
                STATE_BOARD_DESCRIPTION_IN_ENGLISH, date, null, null,
                null, null);
        return event;
    }

    /**
     * Gets board from reference board.
     *
     * @return the board
     */
    Event anEvent(final Event ref, final String title, final String description) {
        LocalDateTime date =
                LocalDateTime.of(2023, Month.MARCH, 10, 0, 0);
        return new Event(ref.id(), title,
                description, date, ref.created_at(), ref.created_by(),
                ref.modified_at(), ref.modified_by());
    }

    Learner anLearner() {
        Learner learner = new Learner(null, "Manikanta",
                "An Description",
                "Image Url", AuthProvider.local, null, null,
                null, null);
        return learner;
    }
}
