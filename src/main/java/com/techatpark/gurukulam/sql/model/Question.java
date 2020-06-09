package com.techatpark.gurukulam.sql.model;

import javax.validation.constraints.NotBlank;

public class Question {
    /**
     * tells the id of question.
     */
    private Integer id;

    /**
     * tells the exam_id of the question.
     */
    private Integer examId;

    /**
     * this is the question.
     */
    @NotBlank(message = "question is mandatory")
    private String question;
    /**
     * tells the answer.
     */
    @NotBlank(message = "answer is mandatory")
    private String answer;

    /**
     * gets the id of question.
     * 
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id of question.
     * 
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * gets the exam id.
     * 
     * @return exam_id
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * sets the examid of question.
     * 
     * @param examId
     */
    public void setExamId(final Integer examId) {
        this.examId = examId;
    }

    /**
     * gets the question.
     * 
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * sets the question.
     * 
     * @param question
     */
    public void setQuestion(final String question) {
        this.question = question;
    }

    /**
     * gets the answer.
     * 
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * sets the answer.
     * 
     * @param answer
     */
    public void setAnswer(final String answer) {
        this.answer = answer;
    }

}
