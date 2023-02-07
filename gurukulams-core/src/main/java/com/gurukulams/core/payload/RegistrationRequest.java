package com.gurukulams.core.payload;

import jakarta.validation.constraints.NotBlank;

/**
 * SignupRequest.
 */

public class RegistrationRequest {

    /**
     * Id.
     */
    @NotBlank
    private String id;

    /**
     * firstName.
     */
    @NotBlank
    private String firstName;

    /**
     * imageUrl.
     */
    @NotBlank
    private String lastName;

    /**
     * getEmail.
     *
     * @return Id
     */
    public String getId() {
        return id;
    }

    /**
     * setEmail.
     *
     * @param theId
     */
    public void setId(final String theId) {
        this.id = theId;
    }

    /**
     * getPassword.
     *
     * @return password
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setPassword.
     *
     * @param thepassword
     */
    public void setFirstName(final String thepassword) {
        this.firstName = thepassword;
    }

    /**
     * get Image Url.
     *
     * @return imageUrl
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets Image Url.
     *
     * @param aimageUrl
     */
    public void setLastName(final String aimageUrl) {
        this.lastName = aimageUrl;
    }
}
