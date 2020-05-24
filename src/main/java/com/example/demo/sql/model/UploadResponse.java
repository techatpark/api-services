package com.example.demo.sql.model;

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
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
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
     * @param content
     */
    public void setContent(final Blob content) {
        this.content = content;
    }

}
