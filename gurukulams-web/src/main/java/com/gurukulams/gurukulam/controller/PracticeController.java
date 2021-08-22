package com.gurukulams.gurukulam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * The type Practice  controller.
 */
@Controller
class PracticeController {

    /**
     * Forwards Practice Requests.
     * @return forward
     */
    @GetMapping("/practices/books/{bookId}/**")
    public String practice(@PathVariable String bookId) {
        return "forward:/practices/"+bookId+"/index.html";
    }
}
