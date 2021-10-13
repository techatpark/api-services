package com.gurukulams.gurukulam.controller;

import com.gurukulams.gurukulam.service.AnswerService;
import com.gurukulams.gurukulam.service.BookService;
import com.gurukulams.gurukulam.service.LearnerService;
import com.gurukulams.gurukulam.service.PracticeService;
import com.gurukulams.gurukulam.service.SyllabusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Institute api controller.
 */
@RestController
@RequestMapping("/api/")
@Tag(name = "Gurukulam Institute", description = "Resource to manage Institute")
class InstituteAPIController {
    /**
     * declare a practiceService.
     */
    private final PracticeService practiceService;
    /**
     * declare a learnerService.
     */
    private final LearnerService learnerService;
    /**
     * declare a syllabusService.
     */
    private final SyllabusService syllabusService;

    /**
     * declare a bookservice.
     */
    private final BookService bookService;
    /**
     * answerService.
     */
    private final AnswerService answerService;

    /**
     * Instantiates a new Book api controller.
     * @param apracticeService  the practice service
     * @param alearnerService the learner service
     * @param asyllabusService the syllabus service
     * @param abookService the book service
     * @param aAnswerService  the answer Service
     */
     InstituteAPIController(final PracticeService apracticeService,
                            final LearnerService alearnerService,
                            final SyllabusService asyllabusService,
                            final BookService abookService,
                            final AnswerService aAnswerService) {
        this.practiceService = apracticeService;
         this.learnerService = alearnerService;
         this.syllabusService = asyllabusService;
         this.bookService = abookService;
        this.answerService = aAnswerService;
    }
}
