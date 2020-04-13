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

import javax.validation.constraints.NotBlank;

/**
 * Validate Username and Token Request Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 29, 2020
 * @since 1.1.Alpha1
 */
public class VerifyTokenOwnerRequest {
    /* Constants: */

    /* Attributes: */
    /**
     * User's username
     */
    @NotBlank
    private String username;
    
    /**
     * User's login token 
     */
    @NotBlank
    private String token;

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
     * Gets <code>token</code>.
     * @return The <code>token</code>.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets <code>token</code>.
     * @param token The <code>token</code> to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "VerifyTokenOwnerRequest [username=" + username + ", token=" + token + "]";
    }
}
