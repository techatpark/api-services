package com.techatpark.starter.security.security.oauth2.user;

import java.util.Map;

/**
 * The type O auth 2 user info.
 */
public abstract class OAuth2UserInfo {
    /**
     * The Attributes.
     */
    protected Map<String, Object> attributes;

    /**
     * Instantiates a new O auth 2 user info.
     *
     * @param attributes the attributes
     */
    public OAuth2UserInfo(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public abstract String getId();

    /**
     * Gets name.
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Gets email.
     *
     * @return the email
     */
    public abstract String getEmail();

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public abstract String getImageUrl();
}
