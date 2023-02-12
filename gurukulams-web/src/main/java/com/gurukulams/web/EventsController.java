package com.gurukulams.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * The type Practice  controller.
 */
@Controller
@RequestMapping("/events")
class EventsController {

    /**
     * Get Events.
     *
     * @return forward
     */
    @GetMapping
    public String events() {
        return "events/index";
    }

    /**
     * Get Event.
     * @param eventId
     * @return forward
     */
    @GetMapping("/{eventId}")
    public String event(@PathVariable final String eventId) {
        return "event/index";
    }

}
