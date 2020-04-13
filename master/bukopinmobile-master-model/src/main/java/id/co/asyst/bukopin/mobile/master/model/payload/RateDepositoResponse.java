/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.payload;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale.Category;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.master.model.entity.Destination;
import id.co.asyst.bukopin.mobile.master.model.entity.PurchaseCategory;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 07, 2020
 * @since 1.1.Alpha1
 */
public class RateDepositoResponse {
    /* Constants: */

    /* Attributes: */
	
	/**
     * Answer
     */
	@JsonProperty("rate")
    private BigDecimal rate;
    
	/**
     * Answer
     */
	@JsonProperty("description")
    private String description;

	/* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>rate</code>.
     * @return The <code>rate</code>.
     */
	public BigDecimal getRate() {
		return rate;
	}

	/**
     * Sets <code>rate</code>.
     * @param rate The <code>rate</code> to set.
     */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
     * Gets <code>description</code>.
     * @return The <code>description</code>.
     */
	public String getDescription() {
		return description;
	}

	/**
     * Sets <code>description</code>.
     * @param description The <code>description</code> to set.
     */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RateDepositoResponse [rate=" + rate + ", description="
				+ description + "]";
	}
	
	

	
	
}
