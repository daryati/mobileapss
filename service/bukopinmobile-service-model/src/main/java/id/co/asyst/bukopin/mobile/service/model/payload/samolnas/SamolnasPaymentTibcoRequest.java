/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.samolnas;

import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseTibco;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 28, 2020
 * @since 1.2.Alpha1
 */
public class SamolnasPaymentTibcoRequest {
    /* Constants: */

    /* Attributes: */
    private IdentityBaseTibco identity;

    private SamolnasPaymentTibcoDataRequest parameter;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>identity</code>.
     * 
     * @return The <code>identity</code>.
     */
    public IdentityBaseTibco getIdentity() {
	return identity;
    }

    /**
     * Sets <code>identity</code>.
     * 
     * @param identity The <code>identity</code> to set.
     */
    public void setIdentity(IdentityBaseTibco identity) {
	this.identity = identity;
    }

    /**
     * Gets <code>parameter</code>.
     * 
     * @return The <code>parameter</code>.
     */
    public SamolnasPaymentTibcoDataRequest getParameter() {
	return parameter;
    }

    /**
     * Sets <code>parameter</code>.
     * 
     * @param parameter The <code>parameter</code> to set.
     */
    public void setParameter(SamolnasPaymentTibcoDataRequest parameter) {
	this.parameter = parameter;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SamolnasPaymentTibcoRequest [identity=" + identity + ", parameter=" + parameter + "]";
    }

}
