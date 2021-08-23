package com.gurukulams.gurukulam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.gurukulam.model.Practice;
import com.gurukulams.gurukulam.service.PracticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * The type Practice  controller.
 */
@Controller
class PracticeController {
    /**
     * Service for Practices.
     */
    private final PracticeService practiceService;

    PracticeController(final PracticeService aPracticeService) {
        this.practiceService = aPracticeService;
    }

    /**
     * Forwards Practice Requests.
     * @param bookId
     * @return forward
     */
    @GetMapping("/practices/books/{bookId}/**")
    public String practice(final @PathVariable String bookId)
            throws JsonProcessingException {
        Practice practice = practiceService.getPracticeByBook(bookId);
        return "forward:/practices/" + bookId + "/index.html";
    }
}
