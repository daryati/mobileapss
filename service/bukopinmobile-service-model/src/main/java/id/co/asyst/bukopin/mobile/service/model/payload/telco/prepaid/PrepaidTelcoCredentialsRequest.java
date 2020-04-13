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

/**
 * Prepaid Telco Credentials Request
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PrepaidTelcoCredentialsRequest {
    /* Constants: */

    /* Attributes: */
    private String clientid;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>clientid</code>.
     * @return The <code>clientid</code>.
     */
    public String getClientid() {
        return clientid;
    }

    /**
     * Sets <code>clientid</code>.
     * @param clientid The <code>clientid</code> to set.
     */
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PrepaidCredentialsRequest [clientid=" + clientid + "]";
    }
}
