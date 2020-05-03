package com.example.demo.sql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * reads from table exame.
     * 
     * @param id
     * @return namespace
     */
    public Optional<Exam> read(final Integer id) {
        Optional<Exam> exam = this.exams.stream().filter(aExam -> id == aExam.getId()).findFirst();
        return exam;
    }

    /**
     * update table exam.
     * 
     * @param id
     * @param exam
     * @return exam
     */
    public Exam update(final Integer id, final Exam exam) {
        this.exams.set(id - 1, exam);
        return exam;
    }

    /**
     * delete exam.
     * 
     * @param id
     * @return exam
     */
    public boolean delete(final Integer id) {
        return this.exams.remove(id - 1) != null;
    }

    /**
     * delete exam.
     * 
     * @param pageNumber
     * @param pageSize
     * @return exam
     */
    public List<Exam> lists(final Integer pageNumber, final Integer pageSize) {
        return this.exams;
    }

}
