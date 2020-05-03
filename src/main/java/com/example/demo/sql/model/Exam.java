package com.example.demo.sql.model;

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

}
