/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid;

/**
 * Prepaid Telco Purchase Inquiry Response
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 19, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PrepaidTelcoPurchaseResponse {
    /* Constants: */

    /* Attributes: */
    private PrepaidTelcoPurchaseResult respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>respayment</code>.
     * @return The <code>respayment</code>.
     */
    public PrepaidTelcoPurchaseResult getRespayment() {
        return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * @param respayment The <code>respayment</code> to set.
     */
    public void setRespayment(PrepaidTelcoPurchaseResult respayment) {
        this.respayment = respayment;
    }

    /* Getters & setters for transient attributes: */

    @Override
	public String toString() {
		return "PrepaidTelcoInquiryResponse [respayment=" + respayment + "]";
	}

    /* Overrides: */
}
