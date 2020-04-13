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
 * @version $Revision$, Mar 16, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TransactionHistoryEmoneyResponse {
    /* Constants: */

    /* Attributes: */
    private String date;
    
    private String time;
    
    private String reference;
    
    private String accountNo;
    
    private String custNo;
    
    private String custName;
    
    private String type;
    
    private BigDecimal amountFee;
    
    private BigDecimal amount;
    
    private BigDecimal totalAmount;
    
    private String alias;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */


    /**
     * Gets <code>date</code>.
     * @return The <code>date</code>.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets <code>date</code>.
     * @param date The <code>date</code> to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets <code>time</code>.
     * @return The <code>time</code>.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets <code>time</code>.
     * @param time The <code>time</code> to set.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets <code>reference</code>.
     * @return The <code>reference</code>.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets <code>reference</code>.
     * @param reference The <code>reference</code> to set.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Gets <code>accountNo</code>.
     * @return The <code>accountNo</code>.
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets <code>accountNo</code>.
     * @param accountNo The <code>accountNo</code> to set.
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * Gets <code>custNo</code>.
     * @return The <code>custNo</code>.
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * Sets <code>custNo</code>.
     * @param custNo The <code>custNo</code> to set.
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * Gets <code>custName</code>.
     * @return The <code>custName</code>.
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets <code>custName</code>.
     * @param custName The <code>custName</code> to set.
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * Gets <code>type</code>.
     * @return The <code>type</code>.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets <code>type</code>.
     * @param type The <code>type</code> to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets <code>amountFee</code>.
     * @return The <code>amountFee</code>.
     */
    public BigDecimal getAmountFee() {
        return amountFee;
    }

    /**
     * Sets <code>amountFee</code>.
     * @param amountFee The <code>amountFee</code> to set.
     */
    public void setAmountFee(BigDecimal amountFee) {
        this.amountFee = amountFee;
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

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TransactionHistoryEmoneyResponse [date=" + date + ", time=" + time + ", reference=" + reference
		+ ", accountNo=" + accountNo + ", custNo=" + custNo + ", custName=" + custName + ", type=" + type
		+ ", amountFee=" + amountFee + ", amount=" + amount + ", totalAmount=" + totalAmount + ", alias="
		+ alias + "]";
    }
    
}
