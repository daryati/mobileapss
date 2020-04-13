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
 * @version $Revision$, Feb 17, 2020
 * @since 1.1.Alpha1
 */
public class TelcoPostpaidPaymentTibcoRequest {
    /* Constants: */

    /* Attributes: */
    private IdentityBaseTibco identity;
    
    private TelcoPostpaidPaymentDataTibcoRequest parameter;

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
     * Gets <code>parameter</code>.
     * @return The <code>parameter</code>.
     */
    public TelcoPostpaidPaymentDataTibcoRequest getParameter() {
        return parameter;
    }

    /**
     * Sets <code>parameter</code>.
     * @param parameter The <code>parameter</code> to set.
     */
    public void setParameter(TelcoPostpaidPaymentDataTibcoRequest parameter) {
        this.parameter = parameter;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelcoPostpaidPaymentTibcoRequest [identity=" + identity + ", parameter=" + parameter + "]";
    }
    
}
