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
package id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy;

import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseCredentialTibco;
import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseTibco;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Feb 24, 2020
 * @since 2.0
 */
public class TelkomPSTNSpeedyPurchaseTibcoRequest {
    /* Constants: */

    /* Attributes: */
    IdentityBaseTibco identity;
    TelkomPSTNSpeedyPurchaseTibcoDataReq parameter;
    
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
    public TelkomPSTNSpeedyPurchaseTibcoDataReq getParameter() {
        return parameter;
    }
    /**
     * Sets <code>parameter</code>.
     * @param parameter The <code>parameter</code> to set.
     */
    public void setParameter(TelkomPSTNSpeedyPurchaseTibcoDataReq parameter) {
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
	return "TelkomPSTNSpeedyPurchaseTibcoRequest [identity=" + identity + ", parameter=" + parameter + "]";
    }
}
