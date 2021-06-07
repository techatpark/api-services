package com.techatpark.gurukulam.chat.model;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
public class ChatMessage {
    /**
     * declare variable type.
     */
    private MessageType type;
    /**
     * declare variable content.
     */
    private String content;
    /**
     * declare variable sender.
     */
    private String sender;

    /**
     * The enum Message type.
     */
    public enum MessageType {
        /**
         * Chat message type.
         */
        CHAT,
        /**
         * Join message type.
         */
        JOIN,
        /**
         * Leave message type.
         */
        LEAVE
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param theType the type
     */
    public void setType(final MessageType theType) {
        this.type = theType;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param theContent the content
     */
    public void setContent(final String theContent) {
        this.content = theContent;
    }

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets sender.
     *
     * @param theSender the sender
     */
    public void setSender(final String theSender) {
        this.sender = theSender;
    }
}
