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
 * EMoney Inquiry Response
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 20, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class EMoneyInquiryResponse {
    /* Constants: */

    /* Attributes: */
    private EMoneyInquiryResult respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>respayment</code>.
     * @return The <code>respayment</code>.
     */
    public EMoneyInquiryResult getRespayment() {
        return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * @param respayment The <code>respayment</code> to set.
     */
    public void setRespayment(EMoneyInquiryResult respayment) {
        this.respayment = respayment;
    }

    /* Getters & setters for transient attributes: */

    @Override
	public String toString() {
		return "EMoneyInquiryResponse [respayment=" + respayment + "]";
	}

    /* Overrides: */
}
