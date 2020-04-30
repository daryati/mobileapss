/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.samolnas;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 27, 2020
 * @since 1.2.Alpha1
 */
public class SamolnasInquiryTibcoResponse {
    /* Constants: */

    /* Attributes: */
    private SamolnasInquiryTibcoRespayment respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>respayment</code>.
     * 
     * @return The <code>respayment</code>.
     */
    public SamolnasInquiryTibcoRespayment getRespayment() {
	return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * 
     * @param respayment The <code>respayment</code> to set.
     */
    public void setRespayment(SamolnasInquiryTibcoRespayment respayment) {
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
	return "SamolnasInquiryTibcoResponse [respayment=" + respayment + "]";
    }

}
