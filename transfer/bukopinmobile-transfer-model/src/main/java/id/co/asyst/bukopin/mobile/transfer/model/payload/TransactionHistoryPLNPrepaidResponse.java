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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Mar 11, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TransactionHistoryPLNPrepaidResponse {
    /* Constants: */

    /* Attributes: */
    private String dateTime;
    
    @JsonProperty("reference")
    private String referenceNumber;
    
    @JsonProperty("accountNo")
    private String accountNumber;
    
    @JsonProperty("subscriberID")
    private String subscriberNumber;
    
    private String subscriberName;
    
    private String powerConsuming;
    
    @JsonProperty("nominal")
    private BigDecimal amount;
    
    @JsonProperty("adminCharge")
    private BigDecimal adminFee;
    
    @JsonProperty("total")
    private BigDecimal totalAmount;
    
    @JsonProperty("purchasedKWH")
    private String totalKwh;
    
    @JsonProperty("stroomToken")
    private String token;
    
    private String alias;

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
     * Gets <code>powerConsuming</code>.
     * @return The <code>powerConsuming</code>.
     */
    public String getPowerConsuming() {
        return powerConsuming;
    }

    /**
     * Sets <code>powerConsuming</code>.
     * @param powerConsuming The <code>powerConsuming</code> to set.
     */
    public void setPowerConsuming(String powerConsuming) {
        this.powerConsuming = powerConsuming;
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
     * Gets <code>totalKwh</code>.
     * @return The <code>totalKwh</code>.
     */
    public String getTotalKwh() {
        return totalKwh;
    }

    /**
     * Sets <code>totalKwh</code>.
     * @param totalKwh The <code>totalKwh</code> to set.
     */
    public void setTotalKwh(String totalKwh) {
        this.totalKwh = totalKwh;
    }

    /**
     * Gets <code>token</code>.
     * @return The <code>token</code>.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets <code>token</code>.
     * @param token The <code>token</code> to set.
     */
    public void setToken(String token) {
        this.token = token;
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

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TransactionHistoryPLNPrepaidResponse [dateTime=" + dateTime + ", referenceNumber=" + referenceNumber
		+ ", accountNumber=" + accountNumber + ", subscriberNumber=" + subscriberNumber + ", subscriberName="
		+ subscriberName + ", powerConsuming=" + powerConsuming + ", amount=" + amount + ", adminFee="
		+ adminFee + ", totalAmount=" + totalAmount + ", totalKwh=" + totalKwh + ", token=" + token + ", alias="
		+ alias + "]";
    }
}
