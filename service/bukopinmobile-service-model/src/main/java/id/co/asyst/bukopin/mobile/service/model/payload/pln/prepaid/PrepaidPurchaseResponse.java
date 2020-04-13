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
 * @version $Revision$, Dec 9, 2019
 * @since 2.0
 */
public class PrepaidPurchaseResponse {
    /* Constants: */

    /* Attributes: */
    private PrepaidPurchaseResult respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>respayment</code>.
     * 
     * @return The <code>respayment</code>.
     */
    public PrepaidPurchaseResult getRespayment() {
	return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * 
     * @param respayment
     *            The <code>respayment</code> to set.
     */
    public void setRespayment(PrepaidPurchaseResult respayment) {
	this.respayment = respayment;
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
	return "PrepaidPurchaseResponse [respayment=" + respayment + "]";
    }
}
