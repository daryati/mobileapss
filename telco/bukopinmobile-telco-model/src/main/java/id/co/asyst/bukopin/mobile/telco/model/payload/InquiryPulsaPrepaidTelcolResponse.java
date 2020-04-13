/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model.payload;

import java.math.BigDecimal;
import java.util.List;
import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14, 2020
 * @since 1.0.Alpha1
 */
public class InquiryPulsaPrepaidTelcolResponse extends InquiryPulsaPrepaidTelcoRequest {
    /* Constants: */

    /* Attributes: */
	
	/**
     * totalAmount
     */
	@JsonProperty("totalAmount")
    private BigDecimal totalAmount;

	/* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>totalAmount</code>.
     * @return The <code>totalAmount</code>.
     */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
     * Sets <code>totalAmount</code>.
     * @param totalAmount The <code>totalAmount</code> to set.
     */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "InquiryPulsaPrepaidTelcolResponse [totalAmount=" + totalAmount
				+ "]";
	}
	
	

	
	
}
