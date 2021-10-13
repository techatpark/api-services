package com.gurukulams.gurukulam.controller;

import com.gurukulams.gurukulam.service.BookService;
import com.gurukulams.gurukulam.service.LearnerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/syllabus")
@Tag(name = "Syllabus", description = "Resource to manage Syllabus")
class SyllabusAPIController {
    /**
     * declare a learnerService.
     */
    private final LearnerService learnerService;
    /**
     * declare a bookservice.
     */
    private final BookService bookService;
    /**
     * Instantiates a new Book api controller.
     * @param alearnerService the learner service
     * @param abookService the book service
     */
    SyllabusAPIController(final LearnerService alearnerService,
                           final BookService abookService) {
        this.learnerService = alearnerService;
        this.bookService = abookService;
    }
}


