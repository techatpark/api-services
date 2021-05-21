package com.techatpark.gurukulam.model;

import javax.validation.constraints.NotBlank;

public class Practice {
    /**
     * tells the id of the exam.
     */
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
    private String owner;

    /**
     * gets the id of exam.
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id of exam.
     *
     * @param anId
     */
    public void setId(final Integer anId) {
        this.id = anId;
    }

    /**
     * gets the name of exam.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the exam name.
     *
     * @param aName
     */
    public void setName(final String aName) {
        this.name = aName;
    }

    /**
     * gets description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets description.
     *
     * @param aDescription
     */
    public void setDescription(final String aDescription) {
        this.description = aDescription;
    }

    /**
     * owner of the exam.
     *
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets owner of the exam.
     *
     * @param theOwner
     */
    public void setOwner(final String theOwner) {
        this.owner = theOwner;
    }
}
