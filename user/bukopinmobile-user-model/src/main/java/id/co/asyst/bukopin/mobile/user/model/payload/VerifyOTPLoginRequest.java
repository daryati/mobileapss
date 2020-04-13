/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.payload;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Verify OTP Login Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 25, 2020
 * @since 1.1.Alpha1
 */
public class VerifyOTPLoginRequest implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 9218657962246177919L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * Username
     */
    @NotBlank
    private String username;
    
    /**
     * OTP
     */
    @NotBlank
    @Size(max=6, message="Maximum OTP length is 6.")
    @Pattern(regexp="[0-9]+", message="OTP should be in numeric.")
    private String otp;

    /* Getters & setters for attributes: */
    /**
     * Gets <code>username</code>.
     * @return The <code>username</code>.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets <code>username</code>.
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets <code>otp</code>.
     * @return The <code>otp</code>.
     */
    public String getOtp() {
        return otp;
    }

    /**
     * Sets <code>otp</code>.
     * @param otp The <code>otp</code> to set.
     */
    public void setOtp(String otp) {
        this.otp = otp;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "VerifyOTPLoginRequest [username=" + username + ", otp=" + otp + "]";
    }
}
