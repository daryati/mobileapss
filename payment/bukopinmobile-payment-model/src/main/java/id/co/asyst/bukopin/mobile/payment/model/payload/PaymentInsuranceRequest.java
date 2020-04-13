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
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.0.Alpha1
 */
public class PaymentInsuranceRequest {
    /* Constants: */

    /* Attributes: */
	@JsonProperty("username")
    private String username;

	@JsonProperty("pin")
	private String pin;
	
	@JsonProperty("codeIns")
	private String codeIns;
	
	@JsonProperty("accountNumber")
	private String accountNumber;
	
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
	
	@JsonProperty("subscriberNumber")
    private String subscriberNumber;
	
	@JsonProperty("element4")
    private String element4;
	
	@JsonProperty("element11")
    private String element11;

	@JsonProperty("element37")
    private String element37;
	
	@JsonProperty("element48")
    private String element48;
	
	@JsonProperty("element63")
    private String element63;
	
	@JsonProperty("element120")
    private String element120;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPrepaidInsurance() {
		return prepaidInsurance;
	}

	public void setPrepaidInsurance(BigDecimal prepaidInsurance) {
		this.prepaidInsurance = prepaidInsurance;
	}

	public BigDecimal getAdminFee() {
		return adminFee;
	}

	public void setAdminFee(BigDecimal adminFee) {
		this.adminFee = adminFee;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getElement4() {
		return element4;
	}

	public void setElement4(String element4) {
		this.element4 = element4;
	}

	public String getElement11() {
		return element11;
	}

	public void setElement11(String element11) {
		this.element11 = element11;
	}

	public String getElement37() {
		return element37;
	}

	public void setElement37(String element37) {
		this.element37 = element37;
	}

	public String getElement48() {
		return element48;
	}

	public void setElement48(String element48) {
		this.element48 = element48;
	}

	public String getElement63() {
		return element63;
	}

	public void setElement63(String element63) {
		this.element63 = element63;
	}

	public String getElement120() {
		return element120;
	}

	public void setElement120(String element120) {
		this.element120 = element120;
	}

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

	@Override
	public String toString() {
		return "PaymentInsuranceRequest [username=" + username + ", pin=" + pin
				+ ", codeIns=" + codeIns + ", accountNumber=" + accountNumber
				+ ", month=" + month + ", amount=" + amount
				+ ", prepaidInsurance=" + prepaidInsurance + ", adminFee="
				+ adminFee + ", totalAmount=" + totalAmount
				+ ", subscriberNumber=" + subscriberNumber + ", element4="
				+ element4 + ", element11=" + element11 + ", element37="
				+ element37 + ", element48=" + element48 + ", element63="
				+ element63 + ", element120=" + element120 + "]";
	}

	
	
	
	
	
	/**
     * Gets <code>accountNumber</code>.
     * @return The <code>accountNumber</code>.
     */
	
	/**
     * Sets <code>accountNumber</code>.
     * @param accountNumber The <code>accountNumber</code> to set.
     */
	
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
