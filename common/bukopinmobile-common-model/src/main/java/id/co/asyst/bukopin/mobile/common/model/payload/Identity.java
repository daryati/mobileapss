/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.model.payload;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Identity Request Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 24, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Identity implements Serializable {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -130123260167789642L;

    /* Constants: */

    /* Attributes: */
    /**
     * Request Date Time (yyyy-MM-dd hh:mm:ss)
     */
    private String reqTime;

    /**
     * Platform used to request
     */
    private String platform;

    /**
     * User Agent
     */
    private String userAgent;

    /**
     * Authentication Token
     */
    private String token;

    /**
     * The Secret Code as key to Encrypt request to Centagate.
     */
    private String secretCode;

    /**
     * device Id
     */
    private String deviceId;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>reqTime</code>.
     * 
     * @return The <code>reqTime</code>.
     */
    public String getReqTime() {
	return reqTime;
    }

    /**
     * Sets <code>reqTime</code>.
     * 
     * @param reqTime
     *            The <code>reqTime</code> to set.
     */
    public void setReqTime(String reqTime) {
	this.reqTime = reqTime;
    }

    /**
     * Gets <code>platform</code>.
     * 
     * @return The <code>platform</code>.
     */
    public String getPlatform() {
	return platform;
    }

    /**
     * Sets <code>platform</code>.
     * 
     * @param platform
     *            The <code>platform</code> to set.
     */
    public void setPlatform(String platform) {
	this.platform = platform;
    }

    /**
     * Gets <code>userAgent</code>.
     * 
     * @return The <code>userAgent</code>.
     */
    public String getUserAgent() {
	return userAgent;
    }

    /**
     * Sets <code>userAgent</code>.
     * 
     * @param userAgent
     *            The <code>userAgent</code> to set.
     */
    public void setUserAgent(String userAgent) {
	this.userAgent = userAgent;
    }

    /**
     * Gets <code>token</code>.
     * 
     * @return The <code>token</code>.
     */
    public String getToken() {
	return token;
    }

    /**
     * Sets <code>token</code>.
     * 
     * @param token
     *            The <code>token</code> to set.
     */
    public void setToken(String token) {
	this.token = token;
    }

    /**
     * Gets <code>secretCode</code>.
     * 
     * @return The <code>secretCode</code>.
     */
    public String getSecretCode() {
	return secretCode;
    }

    /**
     * Sets <code>secretCode</code>.
     * 
     * @param secretCode
     *            The <code>secretCode</code> to set.
     */
    public void setSecretCode(String secretCode) {
	this.secretCode = secretCode;
    }

    public String getDeviceId() {
	return deviceId;
    }

    public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
    }

    /* Functionalities: */

    @Override
    public String toString() {
	return "Identity [reqTime=" + reqTime + ", platform=" + platform + ", userAgent=" + userAgent + ", token="
		+ token + ", secretCode=" + secretCode + ", deviceId=" + deviceId + "]";
    }

}
