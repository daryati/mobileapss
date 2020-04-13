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
 * Update User Status Request Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Nov 5, 2019
 * @since 1.0.Alpha1
 */
public class UpdateUserStatusRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "status is required.")
    private String status;

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
     * Gets <code>status</code>.
     * 
     * @return The <code>status</code>.
     */
    public String getStatus() {
	return status;
    }

    /**
     * Sets <code>status</code>.
     * 
     * @param status
     *            The <code>status</code> to set.
     */
    public void setStatus(String status) {
	this.status = status;
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
	return "UpdateUserStatusRequest [username=" + username + ", status=" + status + "]";
    }
}
