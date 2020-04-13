/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid;

/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 17, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PrepaidAdviceResult {
    /* Constants: */

    /* Attributes: */
    private PrepaidIdentityRequest identity;
    private AdviceDataResponse result;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * 
     * @return The <code>identity</code>.
     */
    public PrepaidIdentityRequest getIdentity() {
	return identity;
    }

    /**
     * Sets <code>identity</code>.
     * 
     * @param identity
     *            The <code>identity</code> to set.
     */
    public void setIdentity(PrepaidIdentityRequest identity) {
	this.identity = identity;
    }

    /**
     * Gets <code>result</code>.
     * 
     * @return The <code>result</code>.
     */
    public AdviceDataResponse getResult() {
	return result;
    }

    /**
     * Sets <code>result</code>.
     * 
     * @param result
     *            The <code>result</code> to set.
     */
    public void setResult(AdviceDataResponse result) {
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
	return "PrepaidAdviceResult [identity=" + identity + ", result=" + result + "]";
    }
}
