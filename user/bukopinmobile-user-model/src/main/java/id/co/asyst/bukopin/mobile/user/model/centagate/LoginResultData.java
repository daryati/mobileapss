/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.centagate;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Field 'object' of Centagate Login Result
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 29, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginResultData {
	/* Constants: */

	/* Attributes: */
	private String appId;
	private String userUniqueId;
	private String passwordExpired;
	private String authToken;
	private String companyName;
	private String sessionTimeout;
	private String groupId;
	private String lastLogin;
	private String timezone;
	private String username;
	private String useSystemPassword;
	private String userClientId;
	
	// TODO handle json using singlequote
	private Map<String,String> roleJson;
	
	 private String lastLogout;
	 private String userId;
	 private String role;
	 private String companyId;
	 private String uuid;
	 private String secretCode;
	 private String userAppId;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */
	/**
	 * Gets <code>appId</code>.
	 * @return The <code>appId</code>.
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * Sets <code>appId</code>.
	 * @param appId The <code>appId</code> to set.
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * Gets <code>userUniqueId</code>.
	 * @return The <code>userUniqueId</code>.
	 */
	public String getUserUniqueId() {
		return userUniqueId;
	}

	/**
	 * Sets <code>userUniqueId</code>.
	 * @param userUniqueId The <code>userUniqueId</code> to set.
	 */
	public void setUserUniqueId(String userUniqueId) {
		this.userUniqueId = userUniqueId;
	}

	/**
	 * Gets <code>passwordExpired</code>.
	 * @return The <code>passwordExpired</code>.
	 */
	public String getPasswordExpired() {
		return passwordExpired;
	}

	/**
	 * Sets <code>passwordExpired</code>.
	 * @param passwordExpired The <code>passwordExpired</code> to set.
	 */
	public void setPasswordExpired(String passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	/**
	 * Gets <code>authToken</code>.
	 * @return The <code>authToken</code>.
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * Sets <code>authToken</code>.
	 * @param authToken The <code>authToken</code> to set.
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * Gets <code>companyName</code>.
	 * @return The <code>companyName</code>.
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets <code>companyName</code>.
	 * @param companyName The <code>companyName</code> to set.
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets <code>sessionTimeout</code>.
	 * @return The <code>sessionTimeout</code>.
	 */
	public String getSessionTimeout() {
		return sessionTimeout;
	}

	/**
	 * Sets <code>sessionTimeout</code>.
	 * @param sessionTimeout The <code>sessionTimeout</code> to set.
	 */
	public void setSessionTimeout(String sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	/**
	 * Gets <code>groupId</code>.
	 * @return The <code>groupId</code>.
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Sets <code>groupId</code>.
	 * @param groupId The <code>groupId</code> to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * Gets <code>lastLogin</code>.
	 * @return The <code>lastLogin</code>.
	 */
	public String getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets <code>lastLogin</code>.
	 * @param lastLogin The <code>lastLogin</code> to set.
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets <code>timezone</code>.
	 * @return The <code>timezone</code>.
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * Sets <code>timezone</code>.
	 * @param timezone The <code>timezone</code> to set.
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
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

	/**
	 * Gets <code>useSystemPassword</code>.
	 * @return The <code>useSystemPassword</code>.
	 */
	public String getUseSystemPassword() {
		return useSystemPassword;
	}

	/**
	 * Sets <code>useSystemPassword</code>.
	 * @param useSystemPassword The <code>useSystemPassword</code> to set.
	 */
	public void setUseSystemPassword(String useSystemPassword) {
		this.useSystemPassword = useSystemPassword;
	}

	/**
	 * Gets <code>userClientId</code>.
	 * @return The <code>userClientId</code>.
	 */
	public String getUserClientId() {
		return userClientId;
	}

	/**
	 * Sets <code>userClientId</code>.
	 * @param userClientId The <code>userClientId</code> to set.
	 */
	public void setUserClientId(String userClientId) {
		this.userClientId = userClientId;
	}
	
	/**
	 * Gets <code>lastLogout</code>.
	 * @return The <code>lastLogout</code>.
	 */
	public String getLastLogout() {
		return lastLogout;
	}

	/**
	 * Sets <code>lastLogout</code>.
	 * @param lastLogout The <code>lastLogout</code> to set.
	 */
	public void setLastLogout(String lastLogout) {
		this.lastLogout = lastLogout;
	}

	/**
	 * Gets <code>userId</code>.
	 * @return The <code>userId</code>.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets <code>userId</code>.
	 * @param userId The <code>userId</code> to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets <code>role</code>.
	 * @return The <code>role</code>.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets <code>role</code>.
	 * @param role The <code>role</code> to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets <code>companyId</code>.
	 * @return The <code>companyId</code>.
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * Sets <code>companyId</code>.
	 * @param companyId The <code>companyId</code> to set.
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * Gets <code>uuid</code>.
	 * @return The <code>uuid</code>.
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets <code>uuid</code>.
	 * @param uuid The <code>uuid</code> to set.
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	 * Gets <code>userAppId</code>.
	 * @return The <code>userAppId</code>.
	 */
	public String getUserAppId() {
		return userAppId;
	}

	/**
	 * Sets <code>userAppId</code>.
	 * @param userAppId The <code>userAppId</code> to set.
	 */
	public void setUserAppId(String userAppId) {
		this.userAppId = userAppId;
	}

	/**
	 * Gets <code>roleJson</code>.
	 * @return The <code>roleJson</code>.
	 */
//	public Map<String, String> getRoleJson() {
//		return roleJson;
//	}

	/**
	 * Sets <code>roleJson</code>.
	 * @param roleJson The <code>roleJson</code> to set.
	 */
//	public void setRoleJson(Map<String, String> roleJson) {
//		this.roleJson = roleJson;
//	}
	
	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginResultData [appId=" + appId + ", userUniqueId=" + userUniqueId + ", passwordExpired="
				+ passwordExpired + ", authToken=" + authToken + ", companyName=" + companyName + ", sessionTimeout="
				+ sessionTimeout + ", groupId=" + groupId + ", lastLogin=" + lastLogin + ", timezone=" + timezone
				+ ", username=" + username + ", useSystemPassword=" + useSystemPassword + ", userClientId="
				+ userClientId 
//				+ ", roleJson=" + roleJson 
				+ ", lastLogout=" + lastLogout + ", userId=" + userId
				+ ", role=" + role + ", companyId=" + companyId + ", uuid=" + uuid + ", secretCode=" + secretCode
				+ ", userAppId=" + userAppId + "]";
	}

}
