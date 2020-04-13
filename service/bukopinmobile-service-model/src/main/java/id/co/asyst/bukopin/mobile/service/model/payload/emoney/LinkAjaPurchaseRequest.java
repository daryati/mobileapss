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
package id.co.asyst.bukopin.mobile.service.model.payload.emoney;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 28, 2020
 * @since 2.0
 */
public class LinkAjaPurchaseRequest {
    /* Constants: */

    /* Attributes: */
    private EMoneyIdentityRequest identity;
    private LinkAjaPurchaseDataRequest parameter;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identityRequest</code>.
     * @return The <code>identityRequest</code>.
     */
    public EMoneyIdentityRequest getIdentity() {
        return identity;
    }
    /**
     * Sets <code>identityRequest</code>.
     * @param identityRequest The <code>identityRequest</code> to set.
     */
    public void setIdentity(EMoneyIdentityRequest identity) {
        this.identity = identity;
    }
    /**
     * Gets <code>parameter</code>.
     * @return The <code>parameter</code>.
     */
    public LinkAjaPurchaseDataRequest getParameter() {
        return parameter;
    }
    /**
     * Sets <code>parameter</code>.
     * @param parameter The <code>parameter</code> to set.
     */
    public void setParameter(LinkAjaPurchaseDataRequest parameter) {
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
	return "LinkAjaPurchaseRequest [identity=" + identity + ", parameter=" + parameter + "]";
    }
    
}
