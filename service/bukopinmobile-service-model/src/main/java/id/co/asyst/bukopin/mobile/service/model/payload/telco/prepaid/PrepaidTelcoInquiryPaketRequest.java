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
 * Prepaid Telco Purchase Inquiry Request
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 19, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PrepaidTelcoInquiryPaketRequest {
    /* Constants: */

    /* Attributes: */
    private PrepaidTelcoIdentityRequest identity;
    private PrepaidTelcoInquiryPaketDataRequest parameter;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * @return The <code>identity</code>.
     */
    public PrepaidTelcoIdentityRequest getIdentity() {
        return identity;
    }
    /**
     * Sets <code>identity</code>.
     * @param identity The <code>identity</code> to set.
     */
    public void setIdentity(PrepaidTelcoIdentityRequest identity) {
        this.identity = identity;
    }
	/**
	 * Gets <code>parameter</code>.
	 * @return The <code>parameter</code>.
	 */
	public PrepaidTelcoInquiryPaketDataRequest getParameter() {
		return parameter;
	}
	/**
	 * Sets <code>parameter</code>.
	 * @param parameter The <code>parameter</code> to set.
	 */
	public void setParameter(PrepaidTelcoInquiryPaketDataRequest parameter) {
		this.parameter = parameter;
	}
    

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
}
