package com.techatpark.gurukulam.controller;

import com.techatpark.gurukulam.model.sql.SqlPractice;
import com.techatpark.gurukulam.service.AnswerService;
import com.techatpark.gurukulam.service.PracticeService;
import com.techatpark.gurukulam.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/practices/sql")
@Tag(name = "Practices", description = "Resource to manage sql practices")
class SqlPracticeController extends PracticeAPIController<SqlPractice> {
    SqlPracticeController(final PracticeService newPracticeService,
                          final QuestionService newQuestionService,
                          final AnswerService newAnswerService) {
        super(newPracticeService, newQuestionService, newAnswerService);
    }
}
