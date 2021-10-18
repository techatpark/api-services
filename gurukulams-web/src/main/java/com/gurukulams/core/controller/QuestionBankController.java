package com.gurukulams.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * The type Practice  controller.
 */
@Controller
class QuestionBankController {

    /**
     * Forwards Practice Requests.
     *
     * @param bookName
     * @return forward
     */
    @GetMapping("/practices/books/{bookName}/**")
    public String questionBank(final @PathVariable String bookName)
            throws JsonProcessingException {
        return "forward:/practices/" + bookName + "/index.html";
    }
}
