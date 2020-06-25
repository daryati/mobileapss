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
 * @author Gibran Haq
 * @version $Revision$, Mar 26, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TransactionHistoryInsuranceResponse {
    /* Constants: */

    /* Attributes: */
    private String dateTime;

    private String referenceNumber;

    private String accountNumber;

    private String subscriberNumber;

    private String subscriberName;

    private String participant;

    private Integer month;

    private BigDecimal amount;

    private BigDecimal prepaidInsurance;

    private BigDecimal adminFee;

    private BigDecimal totalAmount;
    
    private BigDecimal currentAmount;

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
     * Gets <code>participant</code>.
     * @return The <code>participant</code>.
     */
    public String getParticipant() {
        return participant;
    }

    /**
     * Sets <code>participant</code>.
     * @param participant The <code>participant</code> to set.
     */
    public void setParticipant(String participant) {
        this.participant = participant;
    }

    /**
     * Gets <code>month</code>.
     * @return The <code>month</code>.
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Sets <code>month</code>.
     * @param month The <code>month</code> to set.
     */
    public void setMonth(Integer month) {
        this.month = month;
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
     * Gets <code>prepaidInsurance</code>.
     * @return The <code>prepaidInsurance</code>.
     */
    public BigDecimal getPrepaidInsurance() {
        return prepaidInsurance;
    }

    /**
     * Sets <code>prepaidInsurance</code>.
     * @param prepaidInsurance The <code>prepaidInsurance</code> to set.
     */
    public void setPrepaidInsurance(BigDecimal prepaidInsurance) {
        this.prepaidInsurance = prepaidInsurance;
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
    
    

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

    /**
     * Gets <code>currentAmount</code>.
     * @return The <code>currentAmount</code>.
     */
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    /**
     * Sets <code>currentAmount</code>.
     * @param currentAmount The <code>currentAmount</code> to set.
     */
    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TransactionHistoryInsuranceResponse [dateTime=" + dateTime + ", referenceNumber=" + referenceNumber
		+ ", accountNumber=" + accountNumber + ", subscriberNumber=" + subscriberNumber + ", subscriberName="
		+ subscriberName + ", participant=" + participant + ", month=" + month + ", amount=" + amount
		+ ", prepaidInsurance=" + prepaidInsurance + ", adminFee=" + adminFee + ", totalAmount=" + totalAmount
		+ "]";
    }

}
