package com.gurukulams.gurukulam.model;

/**
 * The type Answer choice.
 */
public class Choice {

    /**
     * tells the id.
     */
    private Integer id;

    /**
     * tells the value.
     */
    private String value;

    /**
     * tells whether this is the answer.
     */
    private Boolean answer;

    /**
     * Gets isAnswer.
     *
     * @return the isAnswer
     */
    public Boolean isAnswer() {
        return answer;
    }

    /**
     * Sets isAnswer.
     *
     * @param isAnAnswer the isAnswer
     */
    public void setAnswer(final Boolean isAnAnswer) {
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
