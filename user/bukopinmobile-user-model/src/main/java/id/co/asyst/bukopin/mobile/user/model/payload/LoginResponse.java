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
 * Login Response Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 25, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class LoginResponse {
    /* Constants: */

    /* Attributes: */
    /**
     * Username
     */
    private String username;
    
    /**
     * User's First Name
     */
    private String firstName;
    
    /**
     * User's Last Name
     */
    private String LastName;
    
    /**
     * Token (Hashed for secure storage).
     */
    private String token;
    
    /**
     * Token Timeout
     */
    private String tokenTimeout;
    
    /**
     * Secret Code used as key in hmac.
     */
    private String secretCode;
    
    /**
     * User Activation
     */
    private boolean activation;
    
    /**
     * Is User.phoneIdentity in db equals with request?
     */
    private boolean isSameDevice;

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Functionalities: */
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

    /**
     * Gets <code>secretCode</code>.
     * @return The <code>secretCode</code>.
     */
    public String getSecretCode() {
        return secretCode;
    }

    /**
     * Sets <code>secretCode</code>.
     * @param secretCode The <code>secretCode</code> to set.
     */
    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    /**
     * Gets <code>activation</code>.
     * @return The <code>activation</code>.
     */
    public boolean isActivation() {
        return activation;
    }

    /**
     * Sets <code>activation</code>.
     * @param activation The <code>activation</code> to set.
     */
    public void setActivation(boolean activation) {
        this.activation = activation;
    }
    
    /**
     * Gets <code>tokenTimeout</code>.
     * @return The <code>tokenTimeout</code>.
     */
    public String getTokenTimeout() {
        return tokenTimeout;
    }

    /**
     * Sets <code>tokenTimeout</code>.
     * @param tokenTimeout The <code>tokenTimeout</code> to set.
     */
    public void setTokenTimeout(String tokenTimeout) {
        this.tokenTimeout = tokenTimeout;
    }
    
    /**
     * Gets <code>firstName</code>.
     * @return The <code>firstName</code>.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets <code>firstName</code>.
     * @param firstName The <code>firstName</code> to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets <code>lastName</code>.
     * @return The <code>lastName</code>.
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Sets <code>lastName</code>.
     * @param lastName The <code>lastName</code> to set.
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /**
     * Gets <code>isSameDevice</code>.
     * @return The <code>isSameDevice</code>.
     */
    public boolean isSameDevice() {
        return isSameDevice;
    }

    /**
     * Sets <code>isSameDevice</code>.
     * @param isSameDevice The <code>isSameDevice</code> to set.
     */
    public void setSameDevice(boolean isSameDevice) {
        this.isSameDevice = isSameDevice;
    }

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LoginResponse [username=" + username + ", firstName=" + firstName + ", LastName=" + LastName
		+ ", token=" + token + ", tokenTimeout=" + tokenTimeout + ", secretCode=" + secretCode + ", activation="
		+ activation + ", isSameDevice=" + isSameDevice + "]";
    }
}
