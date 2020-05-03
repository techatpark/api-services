package com.example.demo.sql.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.sql.model.Exam;

import org.springframework.stereotype.Service;

@Service
public class ExamService {

    /**
     * list of exams.
     */
    private final List<Exam> exams = new ArrayList<>();

    /**
     * Create an exam.
     * 
     * @param exam
     * @return exam
     */
    public Exam create(final Exam exam) {
        exam.setId(this.exams.size() + 1);
        this.exams.add(exam);
        return exam;
    }
}
