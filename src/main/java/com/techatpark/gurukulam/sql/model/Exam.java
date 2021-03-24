package com.techatpark.gurukulam.sql.model;

public class Exam {
    /**
     * tells the id of the exam.
     */
    private Integer id;
    /**
     * tells the name of exam.
     */
    private String name;

    /**
     * Database of the exam.
     */
    private Database database;

    /**
     * tells the script of exam.
     */
    private String script;

    /**
     * gets the id of exam.
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id of exam.
     *
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * gets the name of exam.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the exam name.
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * gets databse.
     *
     * @return database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * sets database.
     *
     * @param database
     */
    public void setDatabase(final Database database) {
        this.database = database;
    }

    /**
     * gets script.
     *
     * @return script
     */
    public String getScript() {
        return script;
    }

    /**
     * sets script.
     *
     * @param script
     */
    public void setScript(String script) {
        this.script = script;
    }
}
