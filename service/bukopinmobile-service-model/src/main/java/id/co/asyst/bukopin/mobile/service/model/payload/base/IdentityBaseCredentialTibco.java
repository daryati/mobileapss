/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.base;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 13, 2020
 * @since 1.1.Alpha1
 */
public class IdentityBaseCredentialTibco {
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
	return "IdentityBaseCredentialTibcoRequest [clientid=" + clientid + "]";
    }
    
}
