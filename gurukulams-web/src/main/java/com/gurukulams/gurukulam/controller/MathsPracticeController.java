package com.gurukulams.gurukulam.controller;

import com.gurukulams.gurukulam.model.Practice;
import com.gurukulams.gurukulam.service.AnswerService;
import com.gurukulams.gurukulam.service.PracticeService;
import com.gurukulams.gurukulam.service.QuestionService;
import com.gurukulams.gurukulam.service.UserNoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Maths practice controller.
 */
@RestController
@RequestMapping("/api/practices/maths")
@Tag(name = "Maths Practices",
        description = "Resource to manage maths practices")
class MathsPracticeController extends PracticeAPIController<Practice> {
    /**
     * Instantiates a new Java practice controller.
     *
     * @param newPracticeService the new practice service
     * @param newQuestionService the new question service
     * @param newAnswerService   the new answer service
     * @param userNotesService   the new user note service
     */
    MathsPracticeController(final PracticeService newPracticeService,
                            final QuestionService newQuestionService,
                            final AnswerService newAnswerService,
                            final UserNoteService userNotesService) {
        super(newPracticeService, newQuestionService, newAnswerService,
                userNotesService);
    }

    @Override
    protected String getType() {
        return "maths";
    }
}
