package com.gurukulams.gurukulam.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

/**
 * The type Practice.
 */
public class Practice {
    /**
     * tells the id of the exam.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    /**
     * tells the name of exam.
     */
    @NotBlank(message = "name is mandatory")
    private String name;

    /**
     * tells the description of exam.
     */
    @NotBlank(message = "description is mandatory")
    private String description;

    /**
     * owner of exam.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String owner;

    /**
     * owner of exam.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * owner of exam.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;

    /**
     * gets the id of exam.
     *
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id of exam.
     *
     * @param anId the an id
     */
    public void setId(final Integer anId) {
        this.id = anId;
    }

    /**
     * gets the name of exam.
     *
     * @return name name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the exam name.
     *
     * @param aName the a name
     */
    public void setName(final String aName) {
        this.name = aName;
    }

    /**
     * gets description.
     *
     * @return description description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets description.
     *
     * @param aDescription the a description
     */
    public void setDescription(final String aDescription) {
        this.description = aDescription;
    }

    /**
     * owner of the exam.
     *
     * @return owner owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets owner of the exam.
     *
     * @param theOwner the the owner
     */
    public void setOwner(final String theOwner) {
        this.owner = theOwner;
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
     * @param createdAt the created at
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
