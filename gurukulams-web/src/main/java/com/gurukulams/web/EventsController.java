package com.gurukulams.web;

import com.gurukulams.core.model.Event;
import com.gurukulams.core.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;


/**
 * The type Practice  controller.
 */
@Controller
class EventsController {

    /**
     * declare a event service.
     */
    private final EventService eventService;

    EventsController(final EventService anEventService) {
        this.eventService = anEventService;
    }

    /**
     * Get Events.
     *
     * @return forward
     */
    @GetMapping("/events")
    public String events() {
        return "templates/events/index";
    }

    /**
     * Get Events for locale.
     * @param languageCode
     * @return forward
     */
    @GetMapping("/{languageCode}/events")
    public String eventsLocalized(final @PathVariable String languageCode) {
        return languageCode + "/templates/events/index";
    }

    /**
     * Get Event.
     * @param eventId
     * @param model
     * @return forward
     */
    @GetMapping("/events/{eventId}")
    public String event(@PathVariable final UUID eventId,
                        final Model model) {
        Event event = eventService.read("SYSTEM", null, eventId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid event Id:" + eventId));
        model.addAttribute("event", event);
        return "templates/event/index";
    }

    /**
     * Get Event for locale.
     * @param languageCode
     * @param eventId
     * @param model
     * @return forward
     */
    @GetMapping("/{languageCode}/events/{eventId}")
    public String eventLocalized(final @PathVariable String languageCode,
                                 @PathVariable final UUID eventId,
                                 final Model model) {
        Event event = eventService.read("SYSTEM", null, eventId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid event Id:" + eventId));
        model.addAttribute("event", event);
        return languageCode + "/templates/event/index";
    }
}
