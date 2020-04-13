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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.security.UserToken;

/**
 * Validate Username and Token Response Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 29, 2020
 * @since 1.1.Alpha1
 */
@JsonInclude(Include.NON_NULL)
public class VerifyTokenOwnerResponse {
    /* Constants: */

    /* Attributes: */
    /**
     * Validity status of Token owner verification
     * <br>false, if Username is not the Token owner.
     * <br>true, if Username is the Token owner.
     */
    private boolean valid;
    
    /**
     * User
     */
    private User user;
    
    /**
     * User Token
     */
    private UserToken userToken;

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
     * Gets <code>userToken</code>.
     * @return The <code>userToken</code>.
     */
    public UserToken getUserToken() {
        return userToken;
    }

    /**
     * Sets <code>userToken</code>.
     * @param userToken The <code>userToken</code> to set.
     */
    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "VerifyTokenOwnerResponse [valid=" + valid + ", user=" + user + ", userToken=" + userToken + "]";
    }
}
