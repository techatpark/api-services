package com.techatpark.gurukulam.model;

/**
 * The type Answer choice.
 */
public class Choice {

    /**
     * tells the id.
     */
  private Integer id;
    /**
     * tells the questionId.
     */
    private Integer questionId;
    /**
     * tells the value.
     */
  private String value;

    /**
     * tells whether this is the answer.
     */
  private boolean answer;


    /**
     * Gets isAnswer.
     *
     * @return the isAnswer
     */
    public boolean isAnswer() {
        return answer;
    }

    /**
     * Sets isAnswer.
     *
     * @param isAnAnswer the isAnswer
     */
    public void setAnswer(final boolean isAnAnswer) {
        this.answer = isAnAnswer;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param anId the id
     */
    public void setId(final Integer anId) {
        this.id = anId;
    }

    /**
     * Gets question id.
     *
     * @return the question id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * Sets question id.
     *
     * @param theQuestionId the question id
     */
    public void setQuestionId(final Integer theQuestionId) {
        this.questionId = theQuestionId;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param theValue the value
     */
    public void setValue(final String theValue) {
        this.value = theValue;
    }

}
