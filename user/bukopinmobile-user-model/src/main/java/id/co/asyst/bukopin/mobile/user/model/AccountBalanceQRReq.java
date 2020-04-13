/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Account Balance By QR Code Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 7, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class AccountBalanceQRReq implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7485241249937353823L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * account type.
     */
    @NotNull
    private BigInteger accountType;

    /**
     * Username
     */
    @NotBlank
    private String username;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>accountType</code>.
     * @return The <code>accountType</code>.
     */
    public BigInteger getAccountType() {
        return accountType;
    }

    /**
     * Sets <code>accountType</code>.
     * @param accountType The <code>accountType</code> to set.
     */
    public void setAccountType(BigInteger accountType) {
        this.accountType = accountType;
    }

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

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountBalanceQRReq [accountType=" + accountType + ", username=" + username + "]";
    }

}
