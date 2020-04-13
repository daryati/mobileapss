/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.emoney;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 21, 2020
 * @since 1.1.Alpha1
 */
public class EMoneyPurchaseResult {
    /* Constants: */

    /* Attributes: */
    private EMoneyIdentityRequest identity;
    private EMoneyPurchaseDataResponse result;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * 
     * @return The <code>identity</code>.
     */
    public EMoneyIdentityRequest getIdentity() {
	return identity;
    }

    /**
     * Sets <code>identity</code>.
     * 
     * @param identity
     *            The <code>identity</code> to set.
     */
    public void setIdentity(EMoneyIdentityRequest identity) {
	this.identity = identity;
    }

    /**
     * Gets <code>result</code>.
     * 
     * @return The <code>result</code>.
     */
    public EMoneyPurchaseDataResponse getResult() {
	return result;
    }

    /**
     * Sets <code>result</code>.
     * 
     * @param result
     *            The <code>result</code> to set.
     */
    public void setResult(EMoneyPurchaseDataResponse result) {
	this.result = result;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    @Override
	public String toString() {
		return "EMoneyPurchaseResult [identity=" + identity + ", result="
				+ result + "]";
	}
}
