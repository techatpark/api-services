package com.gurukulams.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * The type User annotation.
 */
public class Annotation {
    /**
     * declare id.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    /**
     * declare onType.
     */
    @JsonIgnore
    private String onType;
    /**
     * declare onInstance.
     */
    @JsonIgnore
    private String onInstance;
    /**
     * declare onSection.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String onSection;

    /**
     * declare text.
     */
    private String text;
    /**
     * declare annotation.
     */
    private String note;


    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param anid the id
     */
    public void setId(final UUID anid) {
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

}
