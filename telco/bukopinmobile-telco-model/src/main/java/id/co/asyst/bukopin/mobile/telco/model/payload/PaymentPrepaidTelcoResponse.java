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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14, 2020
 * @since 1.0.Alpha1
 */
/**
 * @author 216930237
 *
 */
/**
 * @author 216930237
 *
 */
public class PaymentPrepaidTelcoResponse {
    /* Constants: */
	
    /* Attributes: */
	
	/**
     * subscriberName
     */
	@JsonProperty("subscriberName")
    private String subscriberName;

	/**
     * dateTime
     */
	@JsonProperty("dateTime")
	private String dateTime;
	
	/**
     * referenceNumber
     */
	@JsonProperty("referenceNumber")
	private String referenceNumber;
	
	/**
     * accountNumber
     */
	@JsonProperty("accountNumber")
    private String accountNumber;
	
	/**
     * phoneNumber
     */
	@JsonProperty("phoneNumber")
    private String phoneNumber;
	
	/**
     * pGroup
     */
	@JsonProperty("pGroup")
    private String pGroup;
	
	/**
     * adminFee
     */
	@JsonProperty("adminFee")
    private BigDecimal adminFee;
	
	/**
     * amount
     */
	@JsonProperty("amount")
    private BigDecimal amount;
	
	/**
     * totalAmount
     */
	@JsonProperty("totalAmount")
    private BigDecimal totalAmount;
	
	/**
     * idDestination
     */
	@JsonProperty("idDestination")
    private Long idDestination;
	
	/**
     * username
     */
	@JsonIgnore
    private String username;
	
	/**
     * provider
     */
	@JsonProperty("provider")
    private String provider;
	
	/**
     * title
     */
	@JsonProperty("title")
    private String title;
	
	/**
     * institutionType
     */
	@JsonIgnore
    private String institutionType;
	
	/**
     * rrn
     */
	@JsonProperty
    private String rrn;
	
	//	resi
	@JsonProperty
    private String refBayar;
	
//	prodcode
	@JsonProperty
    private String prodCode;
	
	

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
	
	
	
	/**
	 * @return the rrn
	 */
	public String getRrn() {
		return rrn;
	}

	/**
	 * @param rrn the rrn to set
	 */
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	
	
	

	/**
	 * @return the refBayar
	 */
	public String getRefBayar() {
		return refBayar;
	}

	/**
	 * @param refBayar the refBayar to set
	 */
	public void setRefBayar(String refBayar) {
		this.refBayar = refBayar;
	}
	
	
	

	/**
	 * @return the prodCode
	 */
	public String getProdCode() {
		return prodCode;
	}

	/**
	 * @param prodCode the prodCode to set
	 */
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentPrepaidTelcoResponse [subscriberName=" + subscriberName
				+ ", dateTime=" + dateTime + ", referenceNumber="
				+ referenceNumber + ", accountNumber=" + accountNumber
				+ ", phoneNumber=" + phoneNumber + ", pGroup=" + pGroup
				+ ", adminFee=" + adminFee + ", amount=" + amount
				+ ", totalAmount=" + totalAmount + ", idDestination="
				+ idDestination + ", username=" + username + ", provider="
				+ provider + ", title=" + title + ", institutionType="
				+ institutionType + ", rrn=" + rrn + ", refBayar=" + refBayar
				+ ", prodCode=" + prodCode + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	
	
	

//	@Override
//	public String toString() {
//		return "PaymentPrepaidTelcoResponse [subscriberName=" + subscriberName
//				+ ", dateTime=" + dateTime + ", referenceNumber="
//				+ referenceNumber + ", accountNumber=" + accountNumber
//				+ ", phoneNumber=" + phoneNumber + ", pGroup=" + pGroup
//				+ ", adminFee=" + adminFee + ", amount=" + amount
//				+ ", totalAmount=" + totalAmount + ", idDestination="
//				+ idDestination + ", username=" + username + ", provider="
//				+ provider + ", title=" + title + ", institutionType="
//				+ institutionType + "]";
//	}

	
	
}
