package com.techatpark.gurukulam.model;


/**
 * The type User notes.
 */
public class UserNote {
    /**
     * declare id.
     */
    private Integer id;
    /**
     * declare onType.
     */
    private String onType;
    /**
     * declare onInstance.
     */
    private String onInstance;
    /**
     * declare onSection.
     */
    private String onSection;
    /**
     * declare text.
     */
    private String text;
    /**
     * declare notes.
     */
    private String notes;

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
     * @param anid the id
     */
    public void setId(final Integer anid) {
        this.id = anid;
    }

    /**
     * Gets on type.
     *
     * @return the on type
     */
    public String getOnType() {
        return onType;
    }

    /**
     * Sets on type.
     *
     * @param theOnType the on type
     */
    public void setOnType(final String theOnType) {
        this.onType = theOnType;
    }

    /**
     * Gets on instance.
     *
     * @return the on instance
     */
    public String getOnInstance() {
        return onInstance;
    }

    /**
     * Sets on instance.
     *
     * @param theOnInstance the on instance
     */
    public void setOnInstance(final String theOnInstance) {
        this.onInstance = theOnInstance;
    }

    /**
     * Gets on section.
     *
     * @return the on section
     */
    public String getOnSection() {
        return onSection;
    }

    /**
     * Sets on section.
     *
     * @param theOnSection the on section
     */
    public void setOnSection(final String theOnSection) {
        this.onSection = theOnSection;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param theText the text
     */
    public void setText(final String theText) {
        this.text = theText;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param theNotes the notes
     */
    public void setNotes(final String theNotes) {
        this.notes = theNotes;
    }

}
