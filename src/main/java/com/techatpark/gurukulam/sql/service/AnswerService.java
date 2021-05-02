package com.techatpark.gurukulam.sql.service;

import com.techatpark.gurukulam.sql.model.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {

    /**
     * * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * question Service.
     */
    private final QuestionService questionService;

    /**
     * Constructs Answer Service.
     *
     * @param aJdbcTemplate
     * @param anQuestionService
     */
    AnswerService(final JdbcTemplate aJdbcTemplate,
                  final QuestionService anQuestionService) {
        this.jdbcTemplate = aJdbcTemplate;
        this.questionService = anQuestionService;
    }

    /**
     * checks whether the given answer is correct.returns true if correct.
     *
     * @param questionId
     * @param answer
     * @return true
     */
    public final Boolean answer(final Integer questionId,
                                final String answer) {
        Boolean isRigntAnswer = false;
        Optional<Question> question = questionService.read(questionId);
        if (question.isPresent()) {
            String verificationSQL =
                    "SELECT COUNT(*) FROM ( " + question.get().getAnswer()
                            + " except " + answer
                            + " ) AS TOTAL_ROWS";
            Integer count = jdbcTemplate
                    .queryForObject(verificationSQL, Integer.class);
            isRigntAnswer = (count == 0);
        }
        return isRigntAnswer;
    }

}
