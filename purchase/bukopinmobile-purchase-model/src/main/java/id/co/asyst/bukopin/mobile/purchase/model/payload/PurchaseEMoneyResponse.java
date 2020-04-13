/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.payload;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 21, 2020
 * @since 2.0
 */
@JsonInclude(Include.NON_NULL)
public class PurchaseEMoneyResponse {
    /* Constants: */

    /* Attributes: */
	
	 /**
     * customerName
     */
	@JsonProperty("custName")
    private String customerName;
    
	 /**
     * customerNumber
     */
    @JsonProperty("custNo")
    private String customerNumber;

    /**
     * amount
     */
    @JsonProperty("amount")
    private BigDecimal amount;
    
    /**
     * amountFee
     */
    @JsonProperty("amountFee")
    private BigDecimal amountFee;
    
    /**
     * totalAmount
     */
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;
    
    /**
     * date
     */
    @JsonProperty("date")
    private String date;
    
    /**
     * time
     */
    @JsonProperty("time")
    private String time;
    
    /**
     * reference
     */
    @JsonProperty("reference")
    private String reference;
    
    /**
     * accountNumber
     */
    @JsonProperty("accountNo")
    private String accountNumber;
    
    /**
     * username
     */
    @JsonProperty("username")
    private String username;
    
    /**
     * type
     */
    private String type;
    
    /**
     * destinationId
     */
    private String destinationId;
    
    /**
     * receiptField1
     */
    @JsonProperty("receiptField1")
    private String receiptField1;
    
    /**
     * receiptField3
     */
    @JsonProperty("receiptField2")
    private String receiptField2;
    
    /**
     * receiptField3
     */
    @JsonProperty("receiptField3")
    private String receiptField3;
    
    /**
     * receiptField4
     */
    @JsonProperty("receiptField4")
    private String receiptField4;
    
    /**
     * receiptField5
     */
    @JsonProperty("receiptField5")
    private String receiptField5;
    
    /**
     * receiptField6
     */
    @JsonProperty("receiptField6")
    private String receiptField6;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>customerName</code>.
     * 
     * @return The <code>customerName</code>.
     */
	public String getCustomerName() {
		return customerName;
	}

	/**
     * Sets <code>customerName</code>.
     * 
     * @param customerName
     *            The <code>customerName</code> to set.
     */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
     * Gets <code>customerNumber</code>.
     * 
     * @return The <code>customerNumber</code>.
     */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
     * Sets <code>customerNumber</code>.
     * 
     * @param customerNumber
     *            The <code>customerNumber</code> to set.
     */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
     * Gets <code>amount</code>.
     * 
     * @return The <code>amount</code>.
     */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
     * Sets <code>amount</code>.
     * 
     * @param amount
     *            The <code>amount</code> to set.
     */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
     * Gets <code>amountFee</code>.
     * 
     * @return The <code>amountFee</code>.
     */
	public BigDecimal getAmountFee() {
		return amountFee;
	}

	/**
     * Sets <code>amountFee</code>.
     * 
     * @param amountFee
     *            The <code>amountFee</code> to set.
     */
	public void setAmountFee(BigDecimal amountFee) {
		this.amountFee = amountFee;
	}

	/**
     * Gets <code>totalAmount</code>.
     * 
     * @return The <code>totalAmount</code>.
     */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
     * Sets <code>totalAmount</code>.
     * 
     * @param totalAmount
     *            The <code>totalAmount</code> to set.
     */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
     * Gets <code>date</code>.
     * 
     * @return The <code>date</code>.
     */
	public String getDate() {
		return date;
	}

	/**
     * Sets <code>date</code>.
     * 
     * @param date
     *            The <code>date</code> to set.
     */
	public void setDate(String date) {
		this.date = date;
	}

	/**
     * Gets <code>time</code>.
     * 
     * @return The <code>time</code>.
     */
	public String getTime() {
		return time;
	}

	/**
     * Sets <code>time</code>.
     * 
     * @param time
     *            The <code>time</code> to set.
     */
	public void setTime(String time) {
		this.time = time;
	}

	/**
     * Gets <code>reference</code>.
     * 
     * @return The <code>reference</code>.
     */
	public String getReference() {
		return reference;
	}

	/**
     * Sets <code>reference</code>.
     * 
     * @param reference
     *            The <code>reference</code> to set.
     */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
     * Gets <code>accountNumber</code>.
     * 
     * @return The <code>accountNumber</code>.
     */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
     * Sets <code>accountNumber</code>.
     * 
     * @param accountNumber
     *            The <code>accountNumber</code> to set.
     */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	/**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
	public String getType() {
		return type;
	}

	/**
    * Sets <code>type</code>.
    * 
    * @param type
    *            The <code>type</code> to set.
    */
	public void setType(String type) {
		this.type = type;
	}

	/**
     * Gets <code>destinationId</code>.
     * 
     * @return The <code>destinationId</code>.
     */
	public String getDestinationId() {
		return destinationId;
	}

	/**
     * Sets <code>destinationId</code>.
     * 
     * @param destinationId
     *            The <code>destinationId</code> to set.
     */
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	
	/**
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
	public String getUsername() {
		return username;
	}

	/**
     * Sets <code>username</code>.
     * 
     * @param username
     *            The <code>username</code> to set.
     */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets <code>receiptField1</code>.
	 * @return The <code>receiptField1</code>.
	 */
	public String getReceiptField1() {
	    return receiptField1;
	}

	/**
	 * Sets <code>receiptField1</code>.
	 * @param receiptField1 The <code>receiptField1</code> to set.
	 */
	public void setReceiptField1(String receiptField1) {
	    this.receiptField1 = receiptField1;
	}

	/**
	 * Gets <code>receiptField2</code>.
	 * @return The <code>receiptField2</code>.
	 */
	public String getReceiptField2() {
	    return receiptField2;
	}

	/**
	 * Sets <code>receiptField2</code>.
	 * @param receiptField2 The <code>receiptField2</code> to set.
	 */
	public void setReceiptField2(String receiptField2) {
	    this.receiptField2 = receiptField2;
	}

	/**
	 * Gets <code>receiptField3</code>.
	 * @return The <code>receiptField3</code>.
	 */
	public String getReceiptField3() {
	    return receiptField3;
	}

	/**
	 * Sets <code>receiptField3</code>.
	 * @param receiptField3 The <code>receiptField3</code> to set.
	 */
	public void setReceiptField3(String receiptField3) {
	    this.receiptField3 = receiptField3;
	}

	/**
	 * Gets <code>receiptField4</code>.
	 * @return The <code>receiptField4</code>.
	 */
	public String getReceiptField4() {
	    return receiptField4;
	}

	/**
	 * Sets <code>receiptField4</code>.
	 * @param receiptField4 The <code>receiptField4</code> to set.
	 */
	public void setReceiptField4(String receiptField4) {
	    this.receiptField4 = receiptField4;
	}

	/**
	 * Gets <code>receiptField5</code>.
	 * @return The <code>receiptField5</code>.
	 */
	public String getReceiptField5() {
	    return receiptField5;
	}

	/**
	 * Sets <code>receiptField5</code>.
	 * @param receiptField5 The <code>receiptField5</code> to set.
	 */
	public void setReceiptField5(String receiptField5) {
	    this.receiptField5 = receiptField5;
	}

	/**
	 * Gets <code>receiptField6</code>.
	 * @return The <code>receiptField6</code>.
	 */
	public String getReceiptField6() {
	    return receiptField6;
	}

	/**
	 * Sets <code>receiptField6</code>.
	 * @param receiptField6 The <code>receiptField6</code> to set.
	 */
	public void setReceiptField6(String receiptField6) {
	    this.receiptField6 = receiptField6;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "PurchaseEMoneyResponse [customerName=" + customerName + ", customerNumber=" + customerNumber
		    + ", amount=" + amount + ", amountFee=" + amountFee + ", totalAmount=" + totalAmount + ", date="
		    + date + ", time=" + time + ", reference=" + reference + ", accountNumber=" + accountNumber
		    + ", username=" + username + ", type=" + type + ", destinationId=" + destinationId
		    + ", receiptField1=" + receiptField1 + ", receiptField2=" + receiptField2 + ", receiptField3="
		    + receiptField3 + ", receiptField4=" + receiptField4 + ", receiptField5=" + receiptField5
		    + ", receiptField6=" + receiptField6 + "]";
	}

    

}
