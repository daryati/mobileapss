/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.payload;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Feb 3, 2020
 * @since 2.0
 */
public class PurchaseLinkAjaResponse {
    /* Constants: */

    /* Attributes: */
    private String telpNum;
    
    private String custName;
    
    private BigDecimal amountFee;
    
    private BigDecimal amount;
    
    private BigDecimal totalAmount;
    
    private String time;
    
    private String reference;
    
    private String accountNo;
    
    private String type;

    /**
     * Gets <code>telpNum</code>.
     * @return The <code>telpNum</code>.
     */
    public String getTelpNum() {
        return telpNum;
    }

    /**
     * Sets <code>telpNum</code>.
     * @param telpNum The <code>telpNum</code> to set.
     */
    public void setTelpNum(String telpNum) {
        this.telpNum = telpNum;
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

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PurchaseLinkAjaResponse [telpNum=" + telpNum + ", custName=" + custName + ", amountFee=" + amountFee
		+ ", amount=" + amount + ", totalAmount=" + totalAmount + ", time=" + time + ", reference=" + reference
		+ ", accountNo=" + accountNo + ", type=" + type + "]";
    }

}
