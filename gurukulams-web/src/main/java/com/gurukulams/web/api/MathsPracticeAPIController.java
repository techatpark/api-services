package com.gurukulams.web.api;

import com.gurukulams.core.model.Practice;
import com.gurukulams.core.service.AnswerService;
import com.gurukulams.core.service.PracticeService;
import com.gurukulams.core.service.QuestionService;
import com.gurukulams.core.service.UserNoteService;
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
class MathsPracticeAPIController extends PracticeAPIController<Practice> {
    /**
     * Instantiates a new Java practice controller.
     *
     * @param newPracticeService the new practice service
     * @param newQuestionService the new question service
     * @param newAnswerService   the new answer service
     * @param userNotesService   the new user note service
     */
    MathsPracticeAPIController(final PracticeService newPracticeService,
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
