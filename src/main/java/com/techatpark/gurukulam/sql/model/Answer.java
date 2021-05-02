package com.techatpark.gurukulam.sql.model;

public class Answer {

    /**
     * tells the id of studentanswer.
     */
    private Integer id;
    /**
     * Tells the exam id.
     */
    private Integer examId;
    /**
     * tells the question id.
     */
    private Integer questionId;
    /**
     * tells the student answer.
     */
    private String studentAnswer;

    /**
     * gets student answer id.
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets student answer id.
     *
     * @param anId
     */
    public void setId(final Integer anId) {
        this.id = anId;
    }

    /**
     * tells exam id.
     *
     * @return exam id
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * sets exam id.
     *
     * @param anExamId
     */
    public void setExamId(final Integer anExamId) {
        this.examId = anExamId;
    }

    /**
     * gets question id.
     *
     * @return question id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * sets question id.
     *
     * @param anQuestionId
     */
    public void setQuestionId(final Integer anQuestionId) {
        this.questionId = anQuestionId;
    }

    /**
     * gets student answer.
     *
     * @return studentAnswer
     */
    public String getStudentAnswer() {
        return studentAnswer;
    }

    /**
     * sets student answer.
     *
     * @param aStudentAnswer
     */
    public void setStudentAnswer(final String aStudentAnswer) {
        this.studentAnswer = aStudentAnswer;
    }

}
