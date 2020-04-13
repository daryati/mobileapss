/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid;

/**
 * Prepaid Inquiry Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Dec 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PrepaidInquiryRequest {
    /* Constants: */

    /* Attributes: */
    private PrepaidIdentityRequest identity;
    private PrepaidDataRequest parameter;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * @return The <code>identity</code>.
     */
    public PrepaidIdentityRequest getIdentity() {
        return identity;
    }
    /**
     * Sets <code>identity</code>.
     * @param identity The <code>identity</code> to set.
     */
    public void setIdentity(PrepaidIdentityRequest identity) {
        this.identity = identity;
    }
	/**
	 * Gets <code>parameter</code>.
	 * @return The <code>parameter</code>.
	 */
	public PrepaidDataRequest getParameter() {
		return parameter;
	}
	/**
	 * Sets <code>parameter</code>.
	 * @param parameter The <code>parameter</code> to set.
	 */
	public void setParameter(PrepaidDataRequest parameter) {
		this.parameter = parameter;
	}
    

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
}
