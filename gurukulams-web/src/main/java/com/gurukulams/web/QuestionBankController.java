package com.gurukulams.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * The type Practice  controller.
 */
@Controller
class QuestionBankController {

    /**
     * Forwards Practice Requests.
     *
     * @return forward
     */
    @GetMapping("/practices/books/**")
    public String questionBank() {
        return "forward:/practices/basic/index.html";
    }
}
