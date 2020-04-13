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
 * User Profile Response
 * 
 * @author Gibran Haq
 * @version $Revision$, Dec 2, 2019
 * @since 2.0
 */
public class UserProfileResponse {
    /* Constants: */

    /* Attributes: */
    private String profileName;
    
    private String userID;
    
    private String phoneNumber;
    
    private String email;

    /**
     * Gets <code>profileName</code>.
     * @return The <code>profileName</code>.
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Sets <code>profileName</code>.
     * @param profileName The <code>profileName</code> to set.
     */
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    /**
     * Gets <code>userID</code>.
     * @return The <code>userID</code>.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets <code>userID</code>.
     * @param userID The <code>userID</code> to set.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets <code>phoneNumber</code>.
     * @return The <code>phoneNumber</code>.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets <code>phoneNumber</code>.
     * @param phoneNumber The <code>phoneNumber</code> to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets <code>email</code>.
     * @return The <code>email</code>.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets <code>email</code>.
     * @param email The <code>email</code> to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "UserProfileResponse [profileName=" + profileName + ", userID=" + userID + ", phoneNumber=" + phoneNumber
		+ ", email=" + email + "]";
    }
    
}
