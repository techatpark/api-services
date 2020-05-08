package com.example.demo.sql.model;

public class UploadResponse {
    /**
     * tells the file name.
     */
    private String fileName;
    /**
     * tells the URI of file.
     */
    private String fileDownloadUri;
    /**
     * tells the file size.
     */
    private long size;

    /**
     * gets the file name.
     * 
     * @return file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * sets the file name.
     * 
     * @param fileName
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * gets the file donload URI.
     * 
     * @return file download URI
     */
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    /**
     * sets the file download URI.
     * 
     * @param fileDownloadUri
     */
    public void setFileDownloadUri(final String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    /**
     * gets the file size.
     * 
     * @return size
     */
    public long getSize() {
        return size;
    }

    /**
     * sets the file size.
     * 
     * @param size
     */
    public void setSize(final long size) {
        this.size = size;
    }

}
