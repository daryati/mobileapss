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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @param <T>
 * @since 1.0.Alpha1
 */
public class PrefixTelcoDetailMapper<T> {
    /* Constants: */

    /* Attributes: */
	
	/**
     * productCode
     */
	@JsonProperty("productCode")
    private String productCode;
	
	/**
     * productCode
     */
	@JsonProperty("codeCbs")
    private String codeCbs;
	
	/**
     * name
     */
	@JsonProperty("name")
    private String name;
	
	/**
     * institutionType
     */
	@JsonProperty("institutionType")
    private String institutionType;
	
	/**
     * adminFee
     */
	@JsonProperty("adminFee")
    private BigDecimal adminFee;
	
	/**
     * selection
     */
	@JsonProperty("selection")
    private List<T> selection;

	
	/* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>productCode</code>.
     * @return The <code>productCode</code>.
     */
	public String getProductCode() {
		return productCode;
	}

	 /**
     * Sets <code>productCode</code>.
     * @param productCode The <code>productCode</code> to set.
     */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
     * Gets <code>name</code>.
     * @return The <code>name</code>.
     */
	public String getName() {
		return name;
	}

	 /**
     * Sets <code>name</code>.
     * @param name The <code>name</code> to set.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Gets <code>institutionType</code>.
     * @return The <code>institutionType</code>.
     */
	public String getInstitutionType() {
		return institutionType;
	}

	 /**
     * Sets <code>institutionType</code>.
     * @param institutionType The <code>institutionType</code> to set.
     */
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	/**
     * Gets <code>adminFee</code>.
     * @return The <code>adminFee</code>.
     */
	public BigDecimal getAdminFee() {
		return adminFee;
	}

	 /**
     * Sets <code>adminFee</code>.
     * @param adminFee The <code>adminFee</code> to set.
     */
	public void setAdminFee(BigDecimal adminFee) {
		this.adminFee = adminFee;
	}

	/**
     * Gets <code>selection</code>.
     * @return The <code>selection</code>.
     */
	public List<T> getSelection() {
		return selection;
	}

	 /**
     * Sets <code>selection</code>.
     * @param selection The <code>selection</code> to set.
     */
	public void setSelection(List<T> selection) {
		this.selection = selection;
	}
	
	
	/**
     * Gets <code>codeCbs</code>.
     * @return The <code>codeCbs</code>.
     */
	public String getCodeCbs() {
		return codeCbs;
	}

	 /**
     * Sets <code>codeCbs</code>.
     * @param codeCbs The <code>codeCbs</code> to set.
     */
	public void setCodeCbs(String codeCbs) {
		this.codeCbs = codeCbs;
	}

	@Override
	public String toString() {
		return "PrefixTelcoDetailMapper [productCode=" + productCode + ", name="
				+ name + ", institutionType=" + institutionType + ", adminFee="
				+ adminFee + ", selection=" + selection + "]";
	}
	
}
