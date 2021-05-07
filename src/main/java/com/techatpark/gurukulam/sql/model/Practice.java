package com.techatpark.gurukulam.sql.model;

public class Practice {
    /**
     * tells the id of the exam.
     */
    private Integer id;
    /**
     * tells the name of exam.
     */
    private String name;

    /**
     * tells the description of exam.
     */
    private String description;

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
}
