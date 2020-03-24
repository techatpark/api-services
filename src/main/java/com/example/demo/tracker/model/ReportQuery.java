package com.example.demo.tracker.model;

public class ReportQuery extends BaseModel {

    /**
     * tells the name for report query.
     */
    private String name;
    /**
     * tells the description of report query.
     */
    private String description;
    /**
     * tells the report query.
     */
    private String query;

    /**
     * gets the name of report query.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of report query.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * gets the description of report query.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of report query.
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
