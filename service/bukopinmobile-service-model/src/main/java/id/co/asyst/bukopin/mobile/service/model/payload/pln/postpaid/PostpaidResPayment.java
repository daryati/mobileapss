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
 * PLN Postpaid Res Payment Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 16, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PostpaidResPayment {
    /* Constants: */

    /* Attributes: */
    private PrepaidIdentityRequest identity;
    
    private PostpaidDataResponse result;

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
     * Gets <code>result</code>.
     * @return The <code>result</code>.
     */
    public PostpaidDataResponse getResult() {
        return result;
    }

    /**
     * Sets <code>result</code>.
     * @param result The <code>result</code> to set.
     */
    public void setResult(PostpaidDataResponse result) {
        this.result = result;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PostpaidResPayment [identity=" + identity + ", result=" + result + "]";
    }
 }
