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

/**
 * Login App Request model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 31, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class LoginRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String username;

    @NotBlank
    private String password;
    
    /**
     * is Login from biometrik or form? true if using biometrik, else false.
     */
    private Boolean isBio;

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
     * Gets <code>password</code>.
     * 
     * @return The <code>password</code>.
     */
    public String getPassword() {
	return password;
    }

    /**
     * Sets <code>password</code>.
     * 
     * @param password
     *            The <code>password</code> to set.
     */
    public void setPassword(String password) {
	this.password = password;
    }
    
    /**
     * Gets <code>isBio</code>.
     * @return The <code>isBio</code>.
     */
    public Boolean getIsBio() {
        return isBio;
    }

    /**
     * Sets <code>isBio</code>.
     * @param isBio The <code>isBio</code> to set.
     */
    public void setIsBio(Boolean isBio) {
        this.isBio = isBio;
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
	return "LoginRequest [username=" + username + ", password=" + password 
		+ ", isBio=" + isBio + "]";
    }
}
