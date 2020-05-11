package com.example.demo.sql.service;

import java.io.File;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.model.Question;

public interface DatabaseConnector {
    /**
     * verify the given question with the answer.
     * 
     * @param exam
     * @param question
     * @param sqlAnswer
     * @return successflag
     */
    Boolean verify(Exam exam, Question question, String sqlAnswer);

    /**
     * Load the script for the specific exam.
     * 
     * @param exam
     * @param scriptFiles
     * @return successflag
     */
    Boolean loadScript(Exam exam, File[] scriptFiles);
}
