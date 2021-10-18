package com.gurukulams.core.controller.api;

import com.gurukulams.core.model.Practice;
import com.gurukulams.core.service.AnswerService;
import com.gurukulams.core.service.PracticeService;
import com.gurukulams.core.service.QuestionService;
import com.gurukulams.core.service.UserNoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Java practice controller.
 */
//@RestController
@RequestMapping("/api/practices/java")
@Tag(name = "Java Practices",
        description = "Resource to manage java practices")
class JavaPracticeAPIController extends PracticeAPIController<Practice> {
    /**
     * Instantiates a new Java practice controller.
     *
     * @param newPracticeService the new practice service
     * @param newQuestionService the new question service
     * @param newAnswerService   the new answer service
     * @param userNotesService   the new user note service
     */
    JavaPracticeAPIController(final PracticeService newPracticeService,
                              final QuestionService newQuestionService,
                              final AnswerService newAnswerService,
                              final UserNoteService userNotesService) {
        super(newPracticeService, newQuestionService, newAnswerService,
                userNotesService);
    }

    @Override
    protected String getType() {
        return "java";
    }
}
