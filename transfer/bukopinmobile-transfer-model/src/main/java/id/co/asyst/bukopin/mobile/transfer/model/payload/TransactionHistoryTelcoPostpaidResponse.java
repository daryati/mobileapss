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
public class TransactionHistoryTelcoPostpaidResponse {
    /* Constants: */

    /* Attributes: */
    private String date;
    
    private String time;
    
    private String referensi;
    
    private String accountNo;
    
    private String custNo;
    
    private String custName;
    
    private String billPeriode;
    
    private String productName;
    
    private BigDecimal amountFee;
    
    private BigDecimal amount;
    
    private BigDecimal totalAmount;
    
    private String alias;
    
    private String npwp;
    

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
     * Gets <code>referensi</code>.
     * @return The <code>referensi</code>.
     */
    public String getReferensi() {
        return referensi;
    }

    /**
     * Sets <code>referensi</code>.
     * @param referensi The <code>referensi</code> to set.
     */
    public void setReferensi(String referensi) {
        this.referensi = referensi;
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
     * Gets <code>billPeriode</code>.
     * @return The <code>billPeriode</code>.
     */
    public String getBillPeriode() {
        return billPeriode;
    }

    /**
     * Sets <code>billPeriode</code>.
     * @param billPeriode The <code>billPeriode</code> to set.
     */
    public void setBillPeriode(String billPeriode) {
        this.billPeriode = billPeriode;
    }

    /**
     * Gets <code>productName</code>.
     * @return The <code>productName</code>.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets <code>productName</code>.
     * @param productName The <code>productName</code> to set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
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
    
    /**
     * Gets <code>npwp</code>.
     * @return The <code>npwp</code>.
     */
    public String getNpwp() {
        return npwp;
    }

    /**
     * Sets <code>npwp</code>.
     * @param npwp The <code>npwp</code> to set.
     */
    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TransactionHistoryTelcoPostpaidResponse [date=" + date + ", time=" + time + ", referensi=" + referensi
		+ ", accountNo=" + accountNo + ", custNo=" + custNo + ", custName=" + custName + ", billPeriode="
		+ billPeriode + ", productName=" + productName + ", amountFee=" + amountFee + ", amount=" + amount
		+ ", totalAmount=" + totalAmount + ", alias=" + alias + ", npwp=" + npwp + "]";
    }

}
