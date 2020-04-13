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
import java.math.BigInteger;
import java.util.List;
import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 07, 2020
 * @since 1.1.Alpha1
 */
public class SavingsDetailResponse {
    /* Constants: */

    /* Attributes: */
	
	/**
     * Answer
     */
	@JsonProperty("limit")
    private BigInteger limit;
	
	/**
     * Answer
     */
	@JsonProperty("interest")
    private BigInteger interest;
    
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
     * Gets <code>limit</code>.
     * @return The <code>limit</code>.
     */
	public BigInteger getLimit() {
		return limit;
	}

	/**
     * Sets <code>limit</code>.
     * @param limit The <code>limit</code> to set.
     */
	public void setLimit(BigInteger limit) {
		this.limit = limit;
	}

	/**
     * Gets <code>interest</code>.
     * @return The <code>interest</code>.
     */
	public BigInteger getInterest() {
		return interest;
	}

	/**
     * Sets <code>interest</code>.
     * @param interest The <code>interest</code> to set.
     */
	public void setInterest(BigInteger interest) {
		this.interest = interest;
	}

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

	/* Overrides: */
	@Override
	public String toString() {
		return "SavingsDetailResponse [limit=" + limit + ", interest="
				+ interest + ", rate=" + rate + ", description=" + description
				+ "]";
	}
    
	

    
	
}
