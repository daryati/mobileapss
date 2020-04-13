/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.pln;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 11, 2019
 * @since 2.0
 */
public class GetVerifyPINRequest {
    /* Constants: */

    /* Attributes: */
    /**
     * Username
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * PIN
     */
    @NotBlank(message = "PIN is required")
    private String pin;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
    public String getUsername() {
	return username;
    }

    /**
     * Sets <code>username</code>.
     * 
     * @param username
     *            The <code>username</code> to set.
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * Gets <code>pin</code>.
     * 
     * @return The <code>pin</code>.
     */
    public String getPin() {
	return pin;
    }

    /**
     * Sets <code>pin</code>.
     * 
     * @param pin
     *            The <code>pin</code> to set.
     */
    public void setPin(String pin) {
	this.pin = pin;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "VerifyPINRequest [username=" + username + ", pin=" + pin + "]";
    }
}
