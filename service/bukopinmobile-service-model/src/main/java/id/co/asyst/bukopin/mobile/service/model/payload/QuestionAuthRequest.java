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

import javax.validation.constraints.NotBlank;

/**
 * Centagate Security Question Authentication Request Model (PIN Authentication)
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 3, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class QuestionAuthRequest implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5582290785444832722L;
    
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String username;
    @NotBlank
    private String integrationKey;
    @NotBlank
    private String unixTimestamp;
    @NotBlank
    private String data;
    private String authToken;
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
        return unixTimestamp;
    }
    /**
     * Sets <code>unixTimestamp</code>.
     * @param unixTimestamp The <code>unixTimestamp</code> to set.
     */
    public void setUnixTimestamp(String unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
    }
    /**
     * Gets <code>data</code>.
     * @return The <code>data</code>.
     */
    public String getData() {
        return data;
    }
    /**
     * Sets <code>data</code>.
     * @param data The <code>data</code> to set.
     */
    public void setData(String data) {
        this.data = data;
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
	return "QuestionAuthRequest [username=" + username + ", integrationKey=" + integrationKey + ", unixTimestamp="
		+ unixTimestamp + ", data=" + data + ", authToken=" + authToken + ", supportFido=" + supportFido
		+ ", ipAddress=" + ipAddress + ", userAgent=" + userAgent + ", browserFp=" + browserFp + ", hmac="
		+ hmac + "]";
    }
}
