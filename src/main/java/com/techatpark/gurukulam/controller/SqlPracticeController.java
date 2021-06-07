package com.techatpark.gurukulam.controller;

import com.techatpark.gurukulam.model.sql.SqlPractice;
import com.techatpark.gurukulam.service.AnswerService;
import com.techatpark.gurukulam.service.PracticeService;
import com.techatpark.gurukulam.service.QuestionService;
import com.techatpark.gurukulam.service.UserNoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Sql practice controller.
 */
@RestController
@RequestMapping("/api/practices/sql")
@Tag(name = "SQL Practices", description = "Resource to manage sql practices")
class SqlPracticeController extends PracticeAPIController<SqlPractice> {
    /**
     * Instantiates a new Sql practice controller.
     *
     * @param newPracticeService the new practice service
     * @param newQuestionService the new question service
     * @param newAnswerService   the new answer service
     * @param userNotesService   the new user note service
     */
    SqlPracticeController(final PracticeService newPracticeService,
                          final QuestionService newQuestionService,
                          final AnswerService newAnswerService,
                          final UserNoteService userNotesService) {
        super(newPracticeService, newQuestionService, newAnswerService,
                userNotesService);
    }

    @Override
    protected String getType() {
        return "sql";
    }
}
