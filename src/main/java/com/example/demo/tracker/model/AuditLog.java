package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class AuditLog extends BaseModel {
    /**
     * tells the namespace id.
     */
    @NotNull
    private String namespaceId;

    /**
     * tells the table name.
     */
    @NotNull
    private String tableName;

    /**
     * tells the event.
     */
    @NotNull
    private String event;

    /**
     * tells the log1.
     */
    @Null
    private String log1;

    /**
     * tells the log2.
     */
    @Null
    private String log2;

    /**
     * gets the namespace id.
     * 
     * @return namespace id
     */
    public String getNamespaceId() {
        return namespaceId;
    }

    /**
     * sets the namespace id.
     * 
     * @param namespaceId
     */

    public void setNamespaceId(final String namespaceId) {
        this.namespaceId = namespaceId;
    }

    /**
     * gets the tablename.
     * 
     * @return table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * sets the table name.
     * 
     * @param tableName
     */
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    /**
     * gets the event.
     * 
     * @return event
     */
    public String getEvent() {
        return event;
    }

    /**
     * sets the event.
     * 
     * @param event
     */
    public void setEvent(final String event) {
        this.event = event;
    }

    /**
     * gets log 1 details.
     * 
     * @return log 1
     */
    public String getLog1() {
        return log1;
    }

    /**
     * sets log 1.
     * 
     * @param log1
     */
    public void setLog1(final String log1) {
        this.log1 = log1;
    }

    /**
     * gets log 2.
     * 
     * @return log 2
     */
    public String getLog2() {
        return log2;
    }

    /**
     * sets log 2.
     * 
     * @param log2
     */
    public void setLog2(final String log2) {
        this.log2 = log2;
    }

}
