package com.techatpark.sql.model;

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
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
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
     * @param examId
     */
    public void setExamId(final Integer examId) {
        this.examId = examId;
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
     * @param questionId
     */
    public void setQuestionId(final Integer questionId) {
        this.questionId = questionId;
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
     * @param studentAnswer
     */
    public void setStudentAnswer(final String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

}
