package com.techatpark.gurukulam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * The type Practice  controller.
 */
@Controller
class PracticeController {

    /**
     * Forwards Practice Requests
     * @return forward
     */
    @GetMapping("/practices/books")
    public String redirectWithUsingForwardPrefix() {
        return "forward:/practices/java/index.html";
    }
}
