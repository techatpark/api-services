package com.gurukulams.gurukulam.model;

/**
 * The type Syllabus.
 */
public class Syllabus {
    /**
     * declare id.
     */
    private long id;
    /**
     * declare title.
     */
    private static String title;
    /**
     * declare description.
     */
    private static String description;

    /**
     * Gets id.
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * Gets title.
     * @return the title
     */
    public static String getTitle() {
        return title;
    }
    /**
     * Sets id.
     *
     * @param anid an id
     */
    public void setId(final long anid) {
        this.id = anid;
    }
    /**
     * Sets title.
     *
     * @param atitle a title
     */
    public void setTitle(final String atitle) {
        this.title = atitle;
    }
    /**
     * Gets description.
     *
     * @return the description
     */
    public static String getDescription() {
        return description;
    }
    /**
     * Sets description.
     *
     * @param adescription a description
     */
    public void setDescription(final String adescription) {
        this.description = adescription;
    }
}
