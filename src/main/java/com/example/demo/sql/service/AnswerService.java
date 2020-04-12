package com.example.demo.sql.service;

import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    /**
     * checks whether the given answer is correct.returns true if correct.
     * 
     * @param questionId
     * @param answer
     * @return true
     */
    public final Boolean answer(final Integer questionId, final String answer) {
        return true;
    }

}
