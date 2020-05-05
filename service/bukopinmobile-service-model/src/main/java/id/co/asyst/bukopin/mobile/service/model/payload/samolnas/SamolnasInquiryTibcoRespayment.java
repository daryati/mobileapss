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
 * @version $Revision$, Apr 27, 2020
 * @since 1.2.Alpha1
 */
public class SamolnasInquiryTibcoRespayment {
    /* Constants: */

    /* Attributes: */
    private IdentityBaseTibco identity;

    private SamolnasInquiryTibcoDataResult result;

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
     * Gets <code>result</code>.
     * 
     * @return The <code>result</code>.
     */
    public SamolnasInquiryTibcoDataResult getResult() {
	return result;
    }

    /**
     * Sets <code>result</code>.
     * 
     * @param result The <code>result</code> to set.
     */
    public void setResult(SamolnasInquiryTibcoDataResult result) {
	this.result = result;
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
	return "SamolnasInquiryTibcoRespayment [identity=" + identity + ", result=" + result + "]";
    }

}
