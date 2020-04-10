package com.example.demo.sql.model;

public class Question {
    /**
     * tells the id of question.
     */
    private Integer id;
    /**
     * this is the question.
     */
    private String description;
    /**
     * tells the answer.
     */
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
     * gets the description.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description.
     * 
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
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
