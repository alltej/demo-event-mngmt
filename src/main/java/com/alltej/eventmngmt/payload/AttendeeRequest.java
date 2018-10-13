package com.alltej.eventmngmt.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author atejano
 */
public class AttendeeRequest {

    @Email
    @NotBlank
    private String emailAddress;

    @NotBlank
    private String fullName;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
