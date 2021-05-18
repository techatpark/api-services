package com.techatpark.gurukulam.model;

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
     * tells the type of question being created.
     */
    @NotBlank(message = "type of question is mandatory")
    private String type;

    /**
     * gets the type of question.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /***
     * sets the type of question.
     * @param aType
     */
    public void setType(final String aType) {
        this.type = aType;
    }

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
     * @param anId
     */
    public void setId(final Integer anId) {
        this.id = anId;
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
     * @param anExamId
     */
    public void setExamId(final Integer anExamId) {
        this.examId = anExamId;
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
     * @param anQuestion
     */
    public void setQuestion(final String anQuestion) {
        this.question = anQuestion;
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
     * @param anAnswer
     */
    public void setAnswer(final String anAnswer) {
        this.answer = anAnswer;
    }

}
