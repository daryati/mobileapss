/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * Unlock user Request Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Oct 31, 2019
 * @since 1.0.Alpha1
 */
public class UnlockUserRequest implements Serializable {

    /* Constants: */
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */
    @NotBlank
    private String lockedUsername;

    @NotBlank
    private String cenToken;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>lockedUsername</code>.
     * 
     * @return The <code>lockedUsername</code>.
     */
    public String getLockedUsername() {
	return lockedUsername;
    }

    /**
     * Sets <code>lockedUsername</code>.
     * 
     * @param lockedUsername
     *            The <code>lockedUsername</code> to set.
     */
    public void setLockedUsername(String lockedUsername) {
	this.lockedUsername = lockedUsername;
    }

    /**
     * Gets <code>cenToken</code>.
     * 
     * @return The <code>cenToken</code>.
     */
    public String getCenToken() {
	return cenToken;
    }

    /**
     * Sets <code>cenToken</code>.
     * 
     * @param cenToken
     *            The <code>cenToken</code> to set.
     */
    public void setCenToken(String cenToken) {
	this.cenToken = cenToken;
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
	return "UnlockUserRequest [lockedUsername=" + lockedUsername + ", cenToken=" + cenToken + "]";
    }
}
