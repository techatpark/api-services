package com.gurukulams.core.model;

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
     * declare text.
     */
    private String text;


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

}
