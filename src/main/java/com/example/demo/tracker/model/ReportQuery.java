package com.example.demo.tracker.model;

public class ReportQuery extends BaseModel {
    /**
     * tells description about report.
     */
    private String description;
    /**
     * tells the query.
     */
    private String query;

    /**
     * get description of report query.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description.
     * 
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * gets the query.
     * 
     * @return query
     */
    public String getQuery() {
        return query;
    }

    /**
     * sets the query.
     * 
     * @param query
     */
    public void setQuery(final String query) {
        this.query = query;
    }

}
