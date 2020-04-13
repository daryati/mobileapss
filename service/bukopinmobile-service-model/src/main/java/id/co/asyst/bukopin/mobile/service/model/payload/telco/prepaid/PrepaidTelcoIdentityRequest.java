/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid;

import java.util.Date;

/**
 * Prepaid Telco Identity Request
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14,, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PrepaidTelcoIdentityRequest {
    /* Constants: */

    /* Attributes: */
    private Date reqdatetime;
    
    private String clienttxnid;
    
    private PrepaidTelcoCredentialsRequest credentials;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>reqdatetime</code>.
     * @return The <code>reqdatetime</code>.
     */
    public Date getReqdatetime() {
        return reqdatetime;
    }

    /**
     * Sets <code>reqdatetime</code>.
     * @param reqdatetime The <code>reqdatetime</code> to set.
     */
    public void setReqdatetime(Date reqdatetime) {
        this.reqdatetime = reqdatetime;
    }

    /**
     * Gets <code>clienttxnid</code>.
     * @return The <code>clienttxnid</code>.
     */
    public String getClienttxnid() {
        return clienttxnid;
    }

    /**
     * Sets <code>clienttxnid</code>.
     * @param clienttxnid The <code>clienttxnid</code> to set.
     */
    public void setClienttxnid(String clienttxnid) {
        this.clienttxnid = clienttxnid;
    }

	/**
	 * Gets <code>credentials</code>.
	 * @return The <code>credentials</code>.
	 */
	public PrepaidTelcoCredentialsRequest getCredentials() {
		return credentials;
	}

	/**
	 * Sets <code>credentials</code>.
	 * @param credentials The <code>credentials</code> to set.
	 */
	public void setCredentials(PrepaidTelcoCredentialsRequest credentials) {
		this.credentials = credentials;
	}

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
}
