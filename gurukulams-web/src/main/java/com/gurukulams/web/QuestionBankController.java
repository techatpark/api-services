package com.gurukulams.web;

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
     * @return forward
     */
    @GetMapping("/practices/books/**")
    public String questionBank() {
        return "forward:/practices/basic/index.html";
    }

    /**
     * Forwards Practice Requests.
     * @param languageCode
     * @return forward
     */
    @GetMapping("/{languageCode}/practices/books/**")
    public String questionBankLocalized(
            final @PathVariable String languageCode) {
        return "forward:/" + languageCode
                + "/practices/basic/index.html";
    }
}
