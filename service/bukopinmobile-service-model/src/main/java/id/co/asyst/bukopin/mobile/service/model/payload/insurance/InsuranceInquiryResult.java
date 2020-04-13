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
 * EMoney Inquiry Result Model
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 21, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class InsuranceInquiryResult {
    /* Constants: */

    /* Attributes: */
    private InsuranceIdentityRequest identity;
    
    private InsuranceDataResponse result;

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
     * Gets <code>result</code>.
     * @return The <code>result</code>.
     */
    public InsuranceDataResponse getResult() {
        return result;
    }

    /**
     * Sets <code>result</code>.
     * @param result The <code>result</code> to set.
     */
    public void setResult(InsuranceDataResponse result) {
        this.result = result;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    @Override
	public String toString() {
		return "EMoneyInquiryResult [identity=" + identity + ", result="
				+ result + "]";
	}
}
