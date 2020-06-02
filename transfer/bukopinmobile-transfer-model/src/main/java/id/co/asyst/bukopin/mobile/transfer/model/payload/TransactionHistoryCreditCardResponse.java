/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.payload;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, May 08, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TransactionHistoryCreditCardResponse {
    /* Constants: */

    /* Attributes: */
    private String dateTime;
    
    private String referenceNumber;
    
    private String accountNumber;
    
    private String name;
        
    private String subscriberNumber;
    
    private String subscriberName;
    
    private BigDecimal billedAmount;
    
    private BigDecimal minimumPayment;
    
    private BigDecimal amount;
    
    private String alias;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */


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
     * Gets <code>alias</code>.
     * @return The <code>alias</code>.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets <code>alias</code>.
     * @param alias The <code>alias</code> to set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
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
     * Gets <code>subscriberNumber</code>.
     * @return The <code>subscriberNumber</code>.
     */
	public String getSubscriberNumber() {
		return subscriberNumber;
	}

	/**
     * Sets <code>subscriberNumber</code>.
     * @param subscriberNumber The <code>subscriberNumber</code> to set.
     */
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	/**
     * Gets <code>billedAmount</code>.
     * @return The <code>billedAmount</code>.
     */
	public BigDecimal getBilledAmount() {
		return billedAmount;
	}

	/**
     * Sets <code>billedAmount</code>.
     * @param billedAmount The <code>billedAmount</code> to set.
     */
	public void setBilledAmount(BigDecimal billedAmount) {
		this.billedAmount = billedAmount;
	}

	/**
     * Gets <code>minimumPayment</code>.
     * @return The <code>minimumPayment</code>.
     */
	public BigDecimal getMinimumPayment() {
		return minimumPayment;
	}

	/**
     * Sets <code>minimumPayment</code>.
     * @param minimumPayment The <code>minimumPayment</code> to set.
     */
	public void setMinimumPayment(BigDecimal minimumPayment) {
		this.minimumPayment = minimumPayment;
	}

    

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

   
	@Override
	public String toString() {
		return "TransactionHistoryCreditCardResponse [dateTime=" + dateTime + ", referenceNumber=" + referenceNumber
				+ ", accountNumber=" + accountNumber + ", name=" + name + ", subscriberNumber=" + subscriberNumber
				+ ", subscriberName=" + subscriberName + ", billedAmount=" + billedAmount + ", minimumPayment="
				+ minimumPayment + ", amount=" + amount + ", alias=" + alias + "]";
	}
}
