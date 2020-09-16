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
public class PrepaidTelcoInquiryPaketResponse {
    /* Constants: */

    /* Attributes: */
    private PrepaidTelcoInquiryPaketResult respayment;

	
    /* Transient Attributes: */

    /* Constructors: */
    /* Getters & setters for transient attributes: */
    public PrepaidTelcoInquiryPaketResult getRespayment() {
		return respayment;
	}

	public PrepaidTelcoInquiryPaketResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setRespayment(PrepaidTelcoInquiryPaketResult respayment) {
		this.respayment = respayment;
	}

    

    /* Overrides: */
}
