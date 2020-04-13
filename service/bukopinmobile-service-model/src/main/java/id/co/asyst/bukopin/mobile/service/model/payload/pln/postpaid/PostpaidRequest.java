/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid;

import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidIdentityRequest;

/**
 * PLN Payment Request to Tibco
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 16, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PostpaidRequest {
    /* Constants: */

    /* Attributes: */
    private PrepaidIdentityRequest identity;
    
    private PostpaidDataRequest parameter;

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
    public PostpaidDataRequest getParameter() {
        return parameter;
    }

    /**
     * Sets <code>parameter</code>.
     * @param parameter The <code>parameter</code> to set.
     */
    public void setParameter(PostpaidDataRequest parameter) {
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
	return "PaymentBaseRequest [identity=" + identity + ", parameter=" + parameter + "]";
    }
}
