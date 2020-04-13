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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.0.Alpha1
 */
public class PaymentInsuranceResponse {
    /* Constants: */
	
    /* Attributes: */
	@JsonProperty("dateTime")
	private String dateTime;
	
	@JsonProperty("referenceNumber")
	private String referenceNumber;
	
	@JsonProperty("accountNumber")
    private String accountNumber;
	
	@JsonProperty("subscriberNumber")
    private String subscriberNumber;
	
	@JsonProperty("subscriberName")
    private String subscriberName;
	
	@JsonProperty("participant")
    private String participant;
	
	@JsonProperty("month")
    private Integer month;
		
	@JsonProperty("amount")
    private BigDecimal amount;
	
	@JsonProperty("prepaidInsurance")
    private BigDecimal prepaidInsurance;
	
	@JsonProperty("adminFee")
    private BigDecimal adminFee;
	
	@JsonProperty("totalAmount")
    private BigDecimal totalAmount;
	
	@JsonProperty("idDestination")
    private Long idDestination;
	
	@JsonIgnore
    private String username;
	
	@JsonIgnore
    private String codeIns;
	
	
	
	

	/* Transient Attributes: */

    /* Constructors: */
	
	 /* Getters & setters for attributes: */
	
	
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
     * Gets <code>subscriberName</code>.
     * @return The <code>subscriberName</code>.
     */
	public String getSubscriberName() {
		return subscriberName;
	}

	/**
     * Sets <code>subscriberName</code>.
     * @param subscriberName The <code>subscriberName</code> to set.
     */
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	/**
     * Gets <code>dateTime</code>.
     * @return The <code>dateTime</code>.
     */
	public String getDateTime() {
		return dateTime;
	}

	/**
     * Sets <code>dateTime</code>.
     * @param dateTime The <code>dateTime</code> to set.
     */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
     * Gets <code>referenceNumber</code>.
     * @return The <code>referenceNumber</code>.
     */
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
     * Sets <code>referenceNumber</code>.
     * @param referenceNumber The <code>referenceNumber</code> to set.
     */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
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
     * Gets <code>idDestination</code>.
     * @return The <code>idDestination</code>.
     */
	public Long getIdDestination() {
		return idDestination;
	}

	/**
     * Sets <code>idDestination</code>.
     * @param idDestination The <code>idDestination</code> to set.
     */
	public void setIdDestination(Long idDestination) {
		this.idDestination = idDestination;
	}

	public String getSubscriberNumber() {
		return subscriberNumber;
	}

	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public BigDecimal getPrepaidInsurance() {
		return prepaidInsurance;
	}

	public void setPrepaidInsurance(BigDecimal prepaidInsurance) {
		this.prepaidInsurance = prepaidInsurance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCodeIns() {
		return codeIns;
	}

	public void setCodeIns(String codeIns) {
		this.codeIns = codeIns;
	}

	
	
	
	
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
