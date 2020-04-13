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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import javax.validation.constraints.NotBlank;

import org.springframework.util.StringUtils;

/**
 * Login Centagate Request Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 27, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class LoginCentagateRequest implements Serializable {
	/**
	 * Serial Version UID
	 */
//	private static final long serialVersionUID = 7253708833772497718L;
	
	/* Constants: */

	/* Attributes: */
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String integrationKey;
	
	private String unixTimestamp;
	private String supportFido;
	private String ipAddress;
	private String userAgent;
	private String browserFp;
	
	@NotBlank
	private String hmac;

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
	 * Gets <code>password</code>.
	 * @return The <code>password</code>.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets <code>password</code>.
	 * @param password The <code>password</code> to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Gets <code>integrationKey</code>.
	 * @return The <code>integrationKey</code>.
	 */
	public String getIntegrationKey() {
		return integrationKey;
	}
	/**
	 * Sets <code>integrationKey</code>.
	 * @param integrationKey The <code>integrationKey</code> to set.
	 */
	public void setIntegrationKey(String integrationKey) {
		this.integrationKey = integrationKey;
	}
	/**
	 * Gets <code>unixTimestamp</code>.
	 * @return The <code>unixTimestamp</code>.
	 */
	public String getUnixTimestamp() {
		if(StringUtils.hasLength(this.unixTimestamp)) {
			return unixTimestamp;
		} else {
			return String.valueOf(Instant.now().getEpochSecond());
		}
		
	}
	/**
	 * Sets <code>unixTimestamp</code>.
	 * @param unixTimestamp The <code>unixTimestamp</code> to set.
	 */
	public void setUnixTimestamp(String unixTimestamp) {
		this.unixTimestamp = unixTimestamp;
	}
	/**
	 * Gets <code>supportFido</code>.
	 * @return The <code>supportFido</code>.
	 */
	public String getSupportFido() {
		return supportFido;
	}
	/**
	 * Sets <code>supportFido</code>.
	 * @param supportFido The <code>supportFido</code> to set.
	 */
	public void setSupportFido(String supportFido) {
		this.supportFido = supportFido;
	}
	/**
	 * Gets <code>ipAddress</code>.
	 * @return The <code>ipAddress</code>.
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * Sets <code>ipAddress</code>.
	 * @param ipAddress The <code>ipAddress</code> to set.
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * Gets <code>userAgent</code>.
	 * @return The <code>userAgent</code>.
	 */
	public String getUserAgent() {
		return userAgent;
	}
	/**
	 * Sets <code>userAgent</code>.
	 * @param userAgent The <code>userAgent</code> to set.
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	/**
	 * Gets <code>browserFp</code>.
	 * @return The <code>browserFp</code>.
	 */
	public String getBrowserFp() {
		return browserFp;
	}
	/**
	 * Sets <code>browserFp</code>.
	 * @param browserFp The <code>browserFp</code> to set.
	 */
	public void setBrowserFp(String browserFp) {
		this.browserFp = browserFp;
	}
	/**
	 * Gets <code>hmac</code>.
	 * @return The <code>hmac</code>.
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalStateException 
	 */
	public String getHmac() {
		return hmac;
	}
	/**
	 * Sets <code>hmac</code>.
	 * @param hmac The <code>hmac</code> to set.
	 */
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

	/* Functionalities: */

	/* Overrides: */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + ", integrationKey=" + integrationKey
				+ ", unixTimestamp=" + unixTimestamp + ", supportFido=" + supportFido + ", ipAddress=" + ipAddress
				+ ", userAgent=" + userAgent + ", browserFp=" + browserFp + ", hmac=" + hmac + "]";
	}
}
