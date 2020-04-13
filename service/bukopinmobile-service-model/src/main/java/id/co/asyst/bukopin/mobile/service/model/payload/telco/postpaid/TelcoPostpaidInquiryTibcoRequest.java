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
public class TelcoPostpaidInquiryTibcoRequest {
    /* Constants: */

    /* Attributes: */
    private IdentityBaseTibco identity;
    
    private TelcoPostpaidInquiryDataTibcoRequest parameter;

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
     * Gets <code>paramater</code>.
     * @return The <code>paramater</code>.
     */
    public TelcoPostpaidInquiryDataTibcoRequest getParameter() {
        return parameter;
    }

    /**
     * Sets <code>paramater</code>.
     * @param paramater The <code>paramater</code> to set.
     */
    public void setParameter(TelcoPostpaidInquiryDataTibcoRequest parameter) {
        this.parameter = parameter;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
