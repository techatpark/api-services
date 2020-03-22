package com.example.demo.tracker.model;

public class VehicleDriverTopic {
    /**
     * tells the topic.
     */
    private String topic;
    /**
     * tells the low position.
     */
    private int lowPosition;

    /**
     * gets topic.
     * 
     * @return topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * sets topic.
     * 
     * @param topic
     */
    public void setTopic(final String topic) {
        this.topic = topic;
    }

    /**
     * get low position.
     * 
     * @return get low position
     */
    public int getLowPosition() {
        return lowPosition;
    }

    /**
     * set low position.
     * 
     * @param lowPosition
     */
    public void setLowPosition(final int lowPosition) {
        this.lowPosition = lowPosition;
    }
}
