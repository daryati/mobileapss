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
//import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
public class MobileDataSelectionResponse {
    /* Constants: */

    /* Attributes: */
	
	/**
     * title
     */
	@JsonProperty("title")
    private String title;
	
	/**
     * description
     */
	@JsonProperty("description")
    private String description;
	
	/**
     * packageCode
     */
	@JsonProperty("packageCode")
    private String packageCode;
	
	/**
     * amount
     */
	@JsonProperty("amount")
    private BigDecimal amount;


    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>title</code>.
     * @return The <code>title</code>.
     */
	public String getTitle() {
		return title;
	}

	 /**
     * Sets <code>title</code>.
     * @param title The <code>title</code> to set.
     */
	public void setTitle(String title) {
		this.title = title;
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

	/**
     * Gets <code>packageCode</code>.
     * @return The <code>packageCode</code>.
     */
	public String getPackageCode() {
		return packageCode;
	}

	 /**
     * Sets <code>packageCode</code>.
     * @param packageCode The <code>packageCode</code> to set.
     */
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	/**
     * Gets <code>amount</code>.
     * @return The <code>amount</code>.
     */
	public BigDecimal getAmount() {
		return amount;
	}

	 /**
     * Sets <code>amount</code>.
     * @param amount The <code>amount</code> to set.
     */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "MobileDataSelectionResponse [title=" + title + ", description="
				+ description + ", packageCode=" + packageCode + ", amount="
				+ amount + "]";
	}

	
	
}
