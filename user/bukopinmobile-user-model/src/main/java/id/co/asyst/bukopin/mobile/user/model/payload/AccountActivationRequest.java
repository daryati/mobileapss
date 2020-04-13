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
 * Account Activation Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 15, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class AccountActivationRequest {
    /* Constants: */

    /* Attributes: */
    /**
     * Username
     */
    @NotBlank
    private String username;
    
    /**
     * Main Account Number
     */
    @NotBlank
    private String mainAccountNo;

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
     * Gets <code>mainAccountNo</code>.
     * @return The <code>mainAccountNo</code>.
     */
    public String getMainAccountNo() {
        return mainAccountNo;
    }

    /**
     * Sets <code>mainAccountNo</code>.
     * @param mainAccountNo The <code>mainAccountNo</code> to set.
     */
    public void setMainAccountNo(String mainAccountNo) {
        this.mainAccountNo = mainAccountNo;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountActivationRequest [username=" + username + ", mainAccountNo=" + mainAccountNo
		+ "]";
    }
}
