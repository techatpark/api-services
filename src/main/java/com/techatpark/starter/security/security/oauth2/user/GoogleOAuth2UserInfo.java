package com.techatpark.starter.security.security.oauth2.user;

import java.util.Map;

/**
 * The type Google o auth 2 user info.
 */
public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    /**
     * Instantiates a new Google o auth 2 user info.
     *
     * @param attributes the attributes
     */
    public GoogleOAuth2UserInfo(final Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
