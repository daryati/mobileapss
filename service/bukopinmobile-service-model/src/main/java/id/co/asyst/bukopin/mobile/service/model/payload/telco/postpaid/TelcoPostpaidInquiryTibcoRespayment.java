/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid;

import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseTibco;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 13, 2020
 * @since 1.1.Alpha1
 */
public class TelcoPostpaidInquiryTibcoRespayment {
    /* Constants: */

    /* Attributes: */
    private IdentityBaseTibco identity;
    
    private TelcoPostpaidInquiryTibcoDataResult result;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * @return The <code>identity</code>.
     */
    public IdentityBaseTibco getIdentity() {
        return identity;
    }

    /**
     * Sets <code>identity</code>.
     * @param identity The <code>identity</code> to set.
     */
    public void setIdentity(IdentityBaseTibco identity) {
        this.identity = identity;
    }

    /**
     * Gets <code>result</code>.
     * @return The <code>result</code>.
     */
    public TelcoPostpaidInquiryTibcoDataResult getResult() {
        return result;
    }

    /**
     * Sets <code>result</code>.
     * @param result The <code>result</code> to set.
     */
    public void setResult(TelcoPostpaidInquiryTibcoDataResult result) {
        this.result = result;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelcoPostpaidInquiryTibcoRespayment [identity=" + identity + ", result=" + result + "]";
    }
    
}
