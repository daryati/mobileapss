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
 * Prepaid Inquiry Response
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Dec 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PrepaidInquiryResponse {
    /* Constants: */

    /* Attributes: */
    private PrepaidInquiryResult respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>respayment</code>.
     * @return The <code>respayment</code>.
     */
    public PrepaidInquiryResult getRespayment() {
        return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * @param respayment The <code>respayment</code> to set.
     */
    public void setRespayment(PrepaidInquiryResult respayment) {
        this.respayment = respayment;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PrepaidInquiryResponse [respayment=" + respayment + "]";
    }

    /* Overrides: */
}
