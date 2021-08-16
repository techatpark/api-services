package com.gurukulams.gurukulam.model;

import java.sql.Blob;

/**
 * The type Upload response.
 */
public class UploadResponse {
    /**
     * id of file.
     */
    private Integer id;
    /**
     * content of file.
     */
    private Blob content;

    /**
     * gets the id.
     *
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id.
     *
     * @param anId the an id
     */
    public void setId(final Integer anId) {
        this.id = anId;
    }

    /**
     * gets content.
     *
     * @return content content
     */
    public Blob getContent() {
        return content;
    }

    /**
     * set content.
     *
     * @param aContent the a content
     */
    public void setContent(final Blob aContent) {
        this.content = aContent;
    }

}
