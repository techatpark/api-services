package com.techatpark.gurukulam.controller;

import com.techatpark.gurukulam.model.Practice;
import com.techatpark.gurukulam.service.AnswerService;
import com.techatpark.gurukulam.service.PracticeService;
import com.techatpark.gurukulam.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/practices/java")
@Tag(name = "Java Practices",
        description = "Resource to manage java practices")
class JavaPracticeController extends PracticeAPIController<Practice> {
    JavaPracticeController(final PracticeService newPracticeService,
                           final QuestionService newQuestionService,
                           final AnswerService newAnswerService) {
        super(newPracticeService, newQuestionService, newAnswerService);
    }

    @Override
    protected String getType() {
        return "java";
    }
}
