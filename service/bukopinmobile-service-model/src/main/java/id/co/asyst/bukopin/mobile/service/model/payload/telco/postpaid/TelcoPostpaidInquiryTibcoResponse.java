/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 13, 2020
 * @since 1.1.Alpha1
 */
public class TelcoPostpaidInquiryTibcoResponse {
    /* Constants: */

    /* Attributes: */
    private TelcoPostpaidInquiryTibcoRespayment respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>respayment</code>.
     * @return The <code>respayment</code>.
     */
    public TelcoPostpaidInquiryTibcoRespayment getRespayment() {
        return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * @param respayment The <code>respayment</code> to set.
     */
    public void setRespayment(TelcoPostpaidInquiryTibcoRespayment respayment) {
        this.respayment = respayment;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelcoPostpaidInquiryTibcoResponse [respayment=" + respayment + "]";
    }
    
}
