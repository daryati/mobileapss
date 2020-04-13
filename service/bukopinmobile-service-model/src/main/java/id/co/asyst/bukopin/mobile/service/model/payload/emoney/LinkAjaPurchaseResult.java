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
 * @version $Revision$, Jan 30, 2020
 * @since 2.0
 */
public class LinkAjaPurchaseResult {
    /* Constants: */

    /* Attributes: */
    private EMoneyIdentityRequest identity;
    
    private LinkAjaPurchaseDataResponse result;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * @return The <code>identity</code>.
     */
    public EMoneyIdentityRequest getIdentity() {
        return identity;
    }

    /**
     * Sets <code>identity</code>.
     * @param identity The <code>identity</code> to set.
     */
    public void setIdentity(EMoneyIdentityRequest identity) {
        this.identity = identity;
    }

    /**
     * Gets <code>result</code>.
     * @return The <code>result</code>.
     */
    public LinkAjaPurchaseDataResponse getResult() {
        return result;
    }

    /**
     * Sets <code>result</code>.
     * @param result The <code>result</code> to set.
     */
    public void setResult(LinkAjaPurchaseDataResponse result) {
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
	return "LinkAjaPurchaseResult [identity=" + identity + ", result=" + result + "]";
    }

}
