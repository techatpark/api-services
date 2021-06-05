package com.techatpark.gurukulam.model;


/**
 * The type User note.
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
     * Gets prev word.
     *
     * @return the prev word
     */
    public String getPrevWord() {
        return prevWord;
    }

    /**
     * Sets prev word.
     *
     * @param thePrevWord the prev word
     */
    public void setPrevWord(final String thePrevWord) {
        this.prevWord = thePrevWord;
    }

    /**
     * declare onSection.
     */
    private String onSection;
    /**
     * declares variable previous word.
     */
    private String prevWord;
    /**
     * declare text.
     */
    private String text;
    /**
     * declare note.
     */
    private String note;

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
     * Gets note.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets note.
     *
     * @param theNote the note
     */
    public void setNote(final String theNote) {
        this.note = theNote;
    }

    /**
     * Sets text.
     *
     * @param theText the text
     */
    public void setText(final String theText) {
        this.text = theText;
    }



}
