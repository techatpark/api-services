package com.gurukulams.core.payload;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * SignupRequest.
 */

public class RegistrationRequest {


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
     * Date of Birth
     */
    @NotBlank
    private LocalDate dob;


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
