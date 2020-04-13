/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.payload;

import java.math.BigDecimal;
import java.util.List;
import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.0.Alpha1
 */
public class InquiryInsuranceRequest {
    /* Constants: */

    /* Attributes: */
	@JsonProperty("codeIns")
    private String codeIns;
	
	@JsonProperty("subscriberNumber")
    private String subscriberNumber;
	
	@JsonProperty("month")
    private Integer month;

	public String getCodeIns() {
		return codeIns;
	}

	public void setCodeIns(String codeIns) {
		this.codeIns = codeIns;
	}

	public String getSubscriberNumber() {
		return subscriberNumber;
	}

	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
	

	
	
    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>currencyNo</code>.
     * @return The <code>currencyNo</code>.
     */
    

    /**
     * Sets <code>currencyNo</code>.
     * @param currencyNo The <code>currencyNo</code> to set.
     */
	
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
	
}
