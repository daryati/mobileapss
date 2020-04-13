/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.insurance;

/**
 * EMoney Inquiry Request
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Maar 16, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class InsuranceInquiryRequest {
    /* Constants: */

    /* Attributes: */
    private InsuranceIdentityRequest identity;
    private InsuranceDataRequest parameter;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * @return The <code>identity</code>.
     */
    public InsuranceIdentityRequest getIdentity() {
        return identity;
    }
    /**
     * Sets <code>identity</code>.
     * @param identity The <code>identity</code> to set.
     */
    public void setIdentity(InsuranceIdentityRequest identity) {
        this.identity = identity;
    }
	/**
	 * Gets <code>parameter</code>.
	 * @return The <code>parameter</code>.
	 */
	public InsuranceDataRequest getParameter() {
		return parameter;
	}
	/**
	 * Sets <code>parameter</code>.
	 * @param parameter The <code>parameter</code> to set.
	 */
	public void setParameter(InsuranceDataRequest parameter) {
		this.parameter = parameter;
	}
    

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
}
