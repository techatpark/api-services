package com.gurukulams.service;

import com.gurukulams.core.model.Event;
import com.gurukulams.core.service.EventService;
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
public class EventServiceTest {

    public static final String STATE_BOARD_IN_ENGLISH = "State Board";
    public static final String STATE_BOARD_DESCRIPTION_IN_ENGLISH = "State Board Description";
    public static final String STATE_BOARD_TITLE_IN_FRENCH = "Conseil d'État";
    public static final String STATE_BOARD_DESCRIPTION_IN_FRENCH = "Description du conseil d'État";

    @Autowired
    private EventService eventService;

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

        final Event event = eventService.create("mani", null,
                anEvent());
        final UUID newEventId = event.id();
        Event newEvent = new Event(null, "Event", "A " +
                "Event", null, "tom", null, null);
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

        final Event event = eventService.create("mani", null,
                anEvent());
        Event newEvent = new Event(null, "Event New", "A " +
                "Event", null, "tom", null, null);
        eventService.create("mani", null,
                newEvent);
        List<Event> listofEvents = eventService.list("manikanta", null);
        Assertions.assertEquals(2, listofEvents.size());

    }

    /**
     * Gets board.
     *
     * @return the board
     */
    Event anEvent() {
        Event event = new Event(null, STATE_BOARD_IN_ENGLISH,
                STATE_BOARD_DESCRIPTION_IN_ENGLISH, null, null,
                null, null);
        return event;
    }

    /**
     * Gets board from reference board.
     *
     * @return the board
     */
    Event anEvent(final Event ref, final String title, final String description) {
        return new Event(ref.id(), title,
                description, ref.created_at(), ref.created_by(),
                ref.modified_at(), ref.modified_by());
    }
}
