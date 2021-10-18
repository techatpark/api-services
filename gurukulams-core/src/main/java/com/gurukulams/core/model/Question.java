package com.gurukulams.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

/**
 * The type Question.
 */
public class Question {
    /**
     * tells the id of question.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    /**
     * tells the exam_id of the question.
     */
    @JsonIgnore
    private Integer examId;

    /**
     * this is the question.
     */
    @NotBlank(message = "question is mandatory")
    private String question;
    /**
     * tells the answer.
     */
    private String answer;

    /**
     * created_by of question.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdBy;

    /**
     * created_at of question.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;



    /**
     * updated_at of question.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;

    /**
     * tells the type of question being created.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private QuestionType type;

    /**
     * tells the question choices available.
     */
    private List<Choice> choices;

    /**
     * gets the type of question.
     *
     * @return type type
     */
    public QuestionType getType() {
        return type;
    }

    /***
     * sets the type of question.
     *
     * @param aType the a type
     */
    public void setType(final QuestionType aType) {
        this.type = aType;
    }

    /**
     * gets the id of question.
     *
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id of question.
     *
     * @param anId the an id
     */
    public void setId(final Integer anId) {
        this.id = anId;
    }

    /**
     * gets the exam id.
     *
     * @return exam_id exam id
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * sets the examid of question.
     *
     * @param anExamId the an exam id
     */
    public void setExamId(final Integer anExamId) {
        this.examId = anExamId;
    }

    /**
     * gets the question.
     *
     * @return question question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * sets the question.
     *
     * @param anQuestion the an question
     */
    public void setQuestion(final String anQuestion) {
        this.question = anQuestion;
    }

    /**
     * gets the answer.
     *
     * @return answer answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * sets the answer.
     *
     * @param anAnswer the an answer
     */
    public void setAnswer(final String anAnswer) {
        this.answer = anAnswer;
    }

    /**
     * Gets question choice.
     *
     * @return the question choice
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * Sets question choice.
     *
     * @param theChoice the question choice
     */
    public void setChoices(final List<Choice> theChoice) {
        this.choices = theChoice;
    }

    /**
     * created_by of the question.
     *
     * @return created_by created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets created_by of the question.
     *
     * @param theOwner the the created_by
     */
    public void setCreatedBy(final String theOwner) {
        this.createdBy = theOwner;
    }

    /**
     * sets created at.
     * @return createdAt
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * gets created At.
     * @param aCreatedAt the created at
     */
    public void setCreatedAt(final Instant aCreatedAt) {
        this.createdAt = aCreatedAt;
    }

    /**
     * gets updated at.
     * @return updatedAt
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets updated at.
     * @param aupdatedAt
     */
    public void setUpdatedAt(final Instant aupdatedAt) {
        this.updatedAt = aupdatedAt;
    }
}