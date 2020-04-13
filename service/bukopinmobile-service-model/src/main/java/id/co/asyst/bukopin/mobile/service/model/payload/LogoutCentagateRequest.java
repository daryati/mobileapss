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
 * @version $Revision$, Nov 26, 2019
 * @since 1.0.Alpha1
 */
public class LogoutCentagateRequest {
    /* Constants: */

    /* Attributes: */
    private String username;
    
    private String integrationKey;
    
    private String unixTimestamp;
    
    @NotBlank
    private String authToken;
    
    private String hmac;

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

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LogoutCentagateRequest [username=" + username + ", integrationKey=" + integrationKey
		+ ", unixTimestamp=" + unixTimestamp + ", authToken=" + authToken + ", hmac=" + hmac + "]";
    }
    
}
