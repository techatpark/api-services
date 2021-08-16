package com.gurukulams.gurukulam.model.sql;

import com.gurukulams.gurukulam.model.Database;
import com.gurukulams.gurukulam.model.Practice;

/**
 * The type Sql practice.
 */
public class SqlPractice extends Practice {

    /**
     * Database of the exam.
     */
    private Database database;


    /**
     * tells the script of exam.
     */
    private String script;

    /**
     * gets databse.
     *
     * @return database database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * sets database.
     *
     * @param aDatabase the a database
     */
    public void setDatabase(final Database aDatabase) {
        this.database = aDatabase;
    }

    /**
     * gets script.
     *
     * @return script script
     */
    public String getScript() {
        return script;
    }

    /**
     * sets script.
     *
     * @param aScript the a script
     */
    public void setScript(final String aScript) {
        this.script = aScript;
    }
}
