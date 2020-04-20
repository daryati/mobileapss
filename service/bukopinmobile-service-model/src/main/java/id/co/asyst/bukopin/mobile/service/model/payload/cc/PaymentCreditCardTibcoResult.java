/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.cc;

import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseTibco;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 2, 2020
 * @since 2.0
 */
public class PaymentCreditCardTibcoResult {
    /* Constants: */

    /* Attributes: */
    IdentityBaseTibco identity;
    PaymentCreditCardTibcoDataResp result;

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
    public PaymentCreditCardTibcoDataResp getResult() {
        return result;
    }
    /**
     * Sets <code>result</code>.
     * @param result The <code>result</code> to set.
     */
    public void setResult(PaymentCreditCardTibcoDataResp result) {
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
	return "PaymentCreditCardTibcoResult [identity=" + identity + ", result=" + result + "]";
    }
}
