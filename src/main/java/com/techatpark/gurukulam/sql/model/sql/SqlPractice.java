package com.techatpark.gurukulam.sql.model.sql;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Practice;

public class SqlPractice extends Practice {
    /**
     * exam_id - fk.
     */
    private Integer examId;
    /**
     * Database of the exam.
     */
    private Database database;

    /**
     * get examId.
     * @return examid
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * sets exam Id.
     * @param examId
     */
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    /**
     * tells the script of exam.
     */
    private String script;

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
     * @param aDatabase
     */
    public void setDatabase(final Database aDatabase) {
        this.database = aDatabase;
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
     * @param aScript
     */
    public void setScript(final String aScript) {
        this.script = aScript;
    }
}
