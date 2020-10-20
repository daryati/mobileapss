/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.user.model.payload;

import javax.validation.constraints.NotBlank;

/**
 * Send SMS OTP Parameter Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 15, 2020
 * @since 1.4.Alpha1
 */
public class SendOTPSMSRequest {
    /* Constants: */

    /* Attributes: */
    /**
     * User's username
     */
    @NotBlank
    private String username;
    
    /**
     * Application Signature (hashed string need by frontend to autofill SMS OTP)
     */
    private String appSignature;

    /* Transient Attributes: */

    /* Constructors: */

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
     * Gets <code>appSignature</code>.
     * @return The <code>appSignature</code>.
     */
    public String getAppSignature() {
        return appSignature;
    }

    /**
     * Sets <code>appSignature</code>.
     * @param appSignature The <code>appSignature</code> to set.
     */
    public void setAppSignature(String appSignature) {
        this.appSignature = appSignature;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
}
