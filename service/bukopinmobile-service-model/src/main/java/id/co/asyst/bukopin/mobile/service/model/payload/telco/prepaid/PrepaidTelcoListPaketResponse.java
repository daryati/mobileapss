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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Prepaid Telco Purchase Inquiry Response
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 19, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PrepaidTelcoListPaketResponse {
    /* Constants: */

    /* Attributes: */
    private PrepaidTelcoListPaketResult respayment;

	
    /* Transient Attributes: */

    /* Constructors: */
    /* Getters & setters for transient attributes: */
    public PrepaidTelcoListPaketResult getRespayment() {
		return respayment;
	}

	public PrepaidTelcoListPaketResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setRespayment(PrepaidTelcoListPaketResult respayment) {
		this.respayment = respayment;
	}

    

    /* Overrides: */
}
