package com.gurukulams.web.api;

import com.gurukulams.core.model.sql.SqlPractice;
import com.gurukulams.core.service.AnswerService;
import com.gurukulams.core.service.PracticeService;
import com.gurukulams.core.service.QuestionService;
import com.gurukulams.core.service.UserNoteService;

/**
 * The type Sql practice controller.
 */
//@RestController
//@RequestMapping("/api/practices/sql")
//@Tag(name = "SQL Practices", description = "Resource to manage sql
// practices")
class SqlPracticeAPIController extends PracticeAPIController<SqlPractice> {
    /**
     * Instantiates a new Sql practice controller.
     *
     * @param newPracticeService the new practice service
     * @param newQuestionService the new question service
     * @param newAnswerService   the new answer service
     * @param userNotesService   the new user note service
     */
    SqlPracticeAPIController(final PracticeService newPracticeService,
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
