package com.gurukulams.core.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Authentication response.
 */
public final class AuthenticationResponse {
    /**
     * declares variable userName.
     */
    private final String userName;
    /**
     * declares variable authToken.
     */
    private final String authToken;

    /**
     * declares variable expiresIn.
     */
    private final long expiresIn;

    /**
     * declares variable refreshToken.
     */
    private final String refreshToken;
    /**
     * declares variable profilePicture.
     */
    private final String profilePicture;

    /**
     * initializes the value for authToken,refresh_token,profile_pic.
     *
     * @param anUserName      the an user name
     * @param anAuthToken     the an auth token
     * @param anExpiresIn       the anExpiresIn
     * @param aRefreshToken   the a refresh token
     * @param aProfilePicture the a profile picture
     */
    @JsonCreator
    public AuthenticationResponse(
            @JsonProperty("userName") final String anUserName,
            @JsonProperty("authToken") final String anAuthToken,
            @JsonProperty("expires_in") final long anExpiresIn,
            @JsonProperty("refresh_token") final String aRefreshToken,
            @JsonProperty("profile_pic") final String aProfilePicture) {
        this.userName = anUserName;
        this.authToken = anAuthToken;
        this.expiresIn = anExpiresIn;
        this.refreshToken = aRefreshToken;
        this.profilePicture = aProfilePicture;
    }
    /**
     * gets the value for expiresIn.
     *
     * @return expiresIn
     */
    public long getExpiresIn() {
        return expiresIn;
    }

    /**
     * gets the value for auth token.
     *
     * @return auth token
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * gets the value for refresh token.
     *
     * @return refresh token
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * gets the value for profile picture.
     *
     * @return profile picture
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * gets the value for userName.
     *
     * @return userName user name
     */
    public String getUserName() {
        return userName;
    }
}
