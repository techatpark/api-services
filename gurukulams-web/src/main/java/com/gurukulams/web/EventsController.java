package com.gurukulams.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * The type Practice  controller.
 */
@Controller
class EventsController {

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
     * @return forward
     */
    @GetMapping("/events/{eventId}")
    public String event(@PathVariable final String eventId) {
        return "event/index";
    }

}
