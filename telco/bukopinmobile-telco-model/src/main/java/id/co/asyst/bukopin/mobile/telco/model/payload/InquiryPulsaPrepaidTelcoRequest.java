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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14, 2020
 * @since 1.0.Alpha1
 */
public class InquiryPulsaPrepaidTelcoRequest {
    /* Constants: */

    /* Attributes: */
	
	/**
     * phoneNumber
     */
	@NotBlank(message="phone number is required!")
	@JsonProperty("phoneNumber")
    private String phoneNumber;
	
	/**
     * amount
     */
	@NotNull(message="amount is required!")
	@JsonProperty("amount")
    private BigDecimal amount;
	
	/**
     * adminFee
     */
	@NotNull(message="admin fee is required!")
	@JsonProperty("adminFee")
    private BigDecimal adminFee;
	
	/**
     * provider
     */
	@NotBlank(message="provider is required!")
	@JsonProperty("provider")
    private String provider;

	/**
     * pGroup
     */
	@NotBlank(message="provider group is required!")
	@JsonProperty("pGroup")
    private String pGroup;
	
	/**
     * idPrefix
     */
	@NotNull(message="id prefix is required!")
	@JsonProperty("idPrefix")
    private Long idPrefix;
	
	/**
     * institutionType
     */
	@NotBlank(message="institution type is required!")
	@JsonProperty("institutionType")
	private String institutionType;
	
	/**
     * productCode
     */
	//@NotBlank(message="product code is required!")
	@JsonProperty("productCode")
	private String productCode;
	
	/**
     * codeCbs
     */
	//@NotBlank(message="code Cbs is required!")
	@JsonProperty("codeCbs")
	private String codeCbs;
	
	/**
     * packageCode
     */
	//@NotBlank(message="packageCode is required!")
	@JsonProperty("packageCode")
	private String packageCode;

	
	/* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>phoneNumber</code>.
     * @return The <code>phoneNumber</code>.
     */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
     * Sets <code>phoneNumber</code>.
     * @param phoneNumber The <code>phoneNumber</code> to set.
     */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
     * Gets <code>provider</code>.
     * @return The <code>provider</code>.
     */
	public String getProvider() {
		return provider;
	}

	/**
     * Sets <code>provider</code>.
     * @param provider The <code>provider</code> to set.
     */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
     * Gets <code>pGroup</code>.
     * @return The <code>pGroup</code>.
     */
	public String getpGroup() {
		return pGroup;
	}

	/**
     * Sets <code>pGroup</code>.
     * @param pGroup The <code>pGroup</code> to set.
     */
	public void setpGroup(String pGroup) {
		this.pGroup = pGroup;
	}

	/**
     * Gets <code>idPrefix</code>.
     * @return The <code>idPrefix</code>.
     */
	public Long getIdPrefix() {
		return idPrefix;
	}

	/**
     * Sets <code>idPrefix</code>.
     * @param idPrefix The <code>idPrefix</code> to set.
     */
	public void setIdPrefix(Long idPrefix) {
		this.idPrefix = idPrefix;
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

	
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCodeCbs() {
		return codeCbs;
	}

	public void setCodeCbs(String codeCbs) {
		this.codeCbs = codeCbs;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	@Override
	public String toString() {
		return "InquiryPulsaPrepaidTelcoRequest [phoneNumber=" + phoneNumber
				+ ", amount=" + amount + ", adminFee=" + adminFee
				+ ", provider=" + provider + ", pGroup=" + pGroup
				+ ", idPrefix=" + idPrefix + ", institutionType="
				+ institutionType + "]";
	}

	
	
}
