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

/**
 * PLN Payment Response from Tibco
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 16, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PostpaidResponse {
    /* Constants: */

    /* Attributes: */
    private PostpaidResPayment respayment;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>respayment</code>.
     * @return The <code>respayment</code>.
     */
    public PostpaidResPayment getRespayment() {
        return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * @param respayment The <code>respayment</code> to set.
     */
    public void setRespayment(PostpaidResPayment respayment) {
        this.respayment = respayment;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PostpaidResponse [respayment=" + respayment + "]";
    }
}
