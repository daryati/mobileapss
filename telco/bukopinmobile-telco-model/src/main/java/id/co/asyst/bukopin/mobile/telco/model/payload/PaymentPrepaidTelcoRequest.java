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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14, 2020
 * @since 1.0.Alpha1
 */
public class PaymentPrepaidTelcoRequest {
    /* Constants: */

    /* Attributes: */
	
	/**
     * username
     */
	@NotBlank(message="username is required!")
	@JsonProperty("username")
    private String username;

	/**
     * pin
     */
	@NotBlank(message="pin is required!")
	@JsonProperty("pin")
	private String pin;
	
	/**
     * phoneNumber
     */
	@NotBlank(message="phone Number is required!")
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	
	/**
     * amount
     */
	@NotNull(message = "amount is required!")
	@JsonProperty("amount")
    private BigDecimal amount;
	
	/**
     * adminFee
     */
	@NotNull(message = "admin fee is required!")
	@JsonProperty("adminFee")
    private BigDecimal adminFee;
	
	/**
     * totalAmount
     */
	@NotNull(message = "total Amount is required!")
	@JsonProperty("totalAmount")
    private BigDecimal totalAmount;
	
	/**
     * idPrefix
     */
	@NotNull(message = "admin fee is required!")
	@JsonProperty("idPrefix")
    private Long idPrefix;
	
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
     * institutionType
     */
	@NotBlank(message="institution type is required!")
	@JsonProperty("institutionType")
	private String institutionType;
	
	/**
     * accountNumber
     */
	@NotBlank(message="account Number is required!")
	@JsonProperty("accountNumber")
	private String accountNumber;
	
	/**
     * title
     */
	@JsonProperty("title")
	private String title;

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
     * Gets <code>username</code>.
     * @return The <code>username</code>.
     */
	public String getUsername() {
		return username;
	}

	/**
     * Sets <code>username</code>.
     * @param username The <code>username</code> to set.
     */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
     * Gets <code>pinTransaction</code>.
     * @return The <code>pinTransaction</code>.
     */
	public String getPin() {
		return pin;
	}

	/**
     * Sets <code>pinTransaction</code>.
     * @param pinTransaction The <code>pinTransaction</code> to set.
     */
	public void setPin(String pin) {
		this.pin = pin;
	}

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

	/**
     * Gets <code>accountNumber</code>.
     * @return The <code>accountNumber</code>.
     */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
     * Sets <code>accountNumber</code>.
     * @param accountNumber The <code>accountNumber</code> to set.
     */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
