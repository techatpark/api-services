package com.techatpark.gurukulam.model;

import java.sql.Blob;

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
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id.
     *
     * @param anId
     */
    public void setId(final Integer anId) {
        this.id = anId;
    }

    /**
     * gets content.
     *
     * @return content
     */
    public Blob getContent() {
        return content;
    }

    /**
     * set content.
     *
     * @param aContent
     */
    public void setContent(final Blob aContent) {
        this.content = aContent;
    }

}
