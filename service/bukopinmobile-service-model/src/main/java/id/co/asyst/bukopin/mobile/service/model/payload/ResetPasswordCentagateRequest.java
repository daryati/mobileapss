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

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Oct 31, 2019
 * @since 1.0.Alpha1
 */
public class ResetPasswordCentagateRequest {
	/* Constants: */

	/* Attributes: */
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String newPassword;
	
	private String notifyUser;
	
	@NotBlank
	private String cenToken;

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
	 * Gets <code>newPassword</code>.
	 * @return The <code>newPassword</code>.
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets <code>newPassword</code>.
	 * @param newPassword The <code>newPassword</code> to set.
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Gets <code>notifyUser</code>.
	 * @return The <code>notifyUser</code>.
	 */
	public String getNotifyUser() {
		return notifyUser;
	}

	/**
	 * Sets <code>notifyUser</code>.
	 * @param notifyUser The <code>notifyUser</code> to set.
	 */
	public void setNotifyUser(String notifyUser) {
		this.notifyUser = notifyUser;
	}

	/**
	 * Gets <code>cenToken</code>.
	 * @return The <code>cenToken</code>.
	 */
	public String getCenToken() {
		return cenToken;
	}

	/**
	 * Sets <code>cenToken</code>.
	 * @param cenToken The <code>cenToken</code> to set.
	 */
	public void setCenToken(String cenToken) {
		this.cenToken = cenToken;
	}

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResetPasswordCentagateRequest [username=" + username + ", newPassword=" + newPassword + ", notifyUser="
				+ notifyUser + ", cenToken=" + cenToken + "]";
	}
	
}
