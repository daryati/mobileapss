/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Verify OTP Request Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 1, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class VerifyOTPRequest {
    /* Constants: */

    /* Attributes: */
    /**
     * OTP Receiver, it could be Phone Number or Email address.
     */
    @NotBlank
    private String receiver;

    /**
     * Inputed OTP from User
     */
    @NotBlank
    @Size(max=6, message="Maximum OTP length is 6.")
    @Pattern(regexp="[0-9]+", message="OTP should be in numeric.")
    private String otp;

    /* Constructors: */
    /**
     * Gets <code>otp</code>.
     * 
     * @return The <code>otp</code>.
     */
    public String getOtp() {
	return otp;
    }

    /**
     * Gets <code>receiver</code>.
     * 
     * @return The <code>receiver</code>.
     */
    public String getReceiver() {
	return receiver;
    }

    /**
     * Sets <code>receiver</code>.
     * 
     * @param receiver
     *            The <code>receiver</code> to set.
     */
    public void setReceiver(String receiver) {
	this.receiver = receiver;
    }

    /**
     * Sets <code>otp</code>.
     * 
     * @param otp
     *            The <code>otp</code> to set.
     */
    public void setOtp(String otp) {
	this.otp = otp;
    }

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "VerifyOTPRequest [receiver=" + receiver + ", otp=" + otp + "]";
    }
}
