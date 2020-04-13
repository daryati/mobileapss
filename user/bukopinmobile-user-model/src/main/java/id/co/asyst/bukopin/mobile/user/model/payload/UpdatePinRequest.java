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

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 5, 2019
 * @since 2.0
 */
public class UpdatePinRequest {
    /* Constants: */

    /* Attributes: */
    private String username;

    private String defaultPin;

    private String newPin;

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
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * Gets <code>defaultPin</code>.
     * 
     * @return The <code>defaultPin</code>.
     */
    public String getDefaultPin() {
	return defaultPin;
    }

    /**
     * Sets <code>defaultPin</code>.
     * 
     * @param defaultPin The <code>defaultPin</code> to set.
     */
    public void setDefaultPin(String defaultPin) {
	this.defaultPin = defaultPin;
    }

    /**
     * Gets <code>newPin</code>.
     * 
     * @return The <code>newPin</code>.
     */
    public String getNewPin() {
	return newPin;
    }

    /**
     * Sets <code>newPin</code>.
     * 
     * @param newPin The <code>newPin</code> to set.
     */
    public void setNewPin(String newPin) {
	this.newPin = newPin;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    @Override
    public String toString() {
	return "UpdatePinRequest [username = " + username + " Default Pin = " + "New Pin = " + newPin + "]";
    }

}
