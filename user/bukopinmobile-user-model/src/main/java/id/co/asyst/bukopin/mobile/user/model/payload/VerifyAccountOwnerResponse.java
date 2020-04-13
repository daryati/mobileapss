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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * Verify Account Owner Response
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 25, 2020
 * @since 1.1.Alpha1
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class VerifyAccountOwnerResponse {
    /* Constants: */

    /* Attributes: */
    /**
     * Validity status of Account Owner verification
     */
    private boolean valid;
    
    /**
     * User
     */
    private User user;
    
    /**
     * Account Info
     */
    private AccountInfo accountInfo;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>valid</code>.
     * @return The <code>valid</code>.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets <code>valid</code>.
     * @param valid The <code>valid</code> to set.
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * Gets <code>user</code>.
     * @return The <code>user</code>.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets <code>user</code>.
     * @param user The <code>user</code> to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets <code>accountInfo</code>.
     * @return The <code>accountInfo</code>.
     */
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    /**
     * Sets <code>accountInfo</code>.
     * @param accountInfo The <code>accountInfo</code> to set.
     */
    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "VerifyAccountOwnerResponse [valid=" + valid + ", user=" + user + ", accountInfo=" + accountInfo + "]";
    }
}
