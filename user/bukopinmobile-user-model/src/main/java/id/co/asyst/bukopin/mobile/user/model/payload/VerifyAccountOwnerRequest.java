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
 * Verify Account Owner Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 25, 2020
 * @since 1.1.Alpha1
 */
public class VerifyAccountOwnerRequest {
    /* Constants: */

    /* Attributes: */
    /**
     * User's Username
     */
    @NotBlank
    private String username;
    
    /**
     * Account Number
     */
    @NotBlank
    private String accountNo;

    /* Constructors: */
    /**
     * Default Constructor
     */
    public VerifyAccountOwnerRequest() {
	// do nothing
    }
    
    /**
     * Constructor with Username and Account Number
     * 
     * @param username The username to set
     * @param accountNo The Account Number to set
     */
    public VerifyAccountOwnerRequest(String username, String accountNo) {
	this.username = username;
	this.accountNo = accountNo;
    }

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
     * Gets <code>accountNo</code>.
     * @return The <code>accountNo</code>.
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets <code>accountNo</code>.
     * @param accountNo The <code>accountNo</code> to set.
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "VerifyAccountOwnerRequest [username=" + username + ", accountNo=" + accountNo + "]";
    }
}
