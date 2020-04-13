/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;

import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * PLN Postpaid Entity Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 23, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Entity
@Table(name="PLN_POSTPAID")
public class PLNPostpaid extends IdBasedObject {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 9079370632245838564L;
    
    /* Constants: */

    /* Attributes: */
    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 50)
    private String accountNumber;
    
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    
    @Column(name = "ADMIN_FEE", nullable = false)
    private BigDecimal adminFee;
    
    @Column(name = "PENALTY_FEE", nullable = false)
    private BigDecimal penaltyFee;
    
    @Column(name = "TARIF", nullable = false, length = 50)
    private String tarif;
    
    @Column(name = "DAYA", nullable = false, length = 50)
    private String daya;
    
    @Column(name = "METER_START", nullable = false)
    private BigInteger meterStart;
    
    @Column(name = "METER_END", nullable = false)
    private BigInteger meterEnd;
    
    @Column(name = "BULAN", nullable = false, length = 255)
    private String bulan;
    
    @Column(name = "TOTAL_BILL", nullable = false)
    private int totalBill;
    
    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;
    
    @OneToOne
    @JoinColumn(name="TRANSACTION_ID")
    private Transaction transaction;

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * Gets <code>penaltyFee</code>.
     * @return The <code>penaltyFee</code>.
     */
    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    /**
     * Sets <code>penaltyFee</code>.
     * @param penaltyFee The <code>penaltyFee</code> to set.
     */
    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    /**
     * Gets <code>tarif</code>.
     * @return The <code>tarif</code>.
     */
    public String getTarif() {
        return tarif;
    }

    /**
     * Sets <code>tarif</code>.
     * @param tarif The <code>tarif</code> to set.
     */
    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    /**
     * Gets <code>daya</code>.
     * @return The <code>daya</code>.
     */
    public String getDaya() {
        return daya;
    }

    /**
     * Sets <code>daya</code>.
     * @param daya The <code>daya</code> to set.
     */
    public void setDaya(String daya) {
        this.daya = daya;
    }

    /**
     * Gets <code>meterStart</code>.
     * @return The <code>meterStart</code>.
     */
    public BigInteger getMeterStart() {
        return meterStart;
    }

    /**
     * Sets <code>meterStart</code>.
     * @param meterStart The <code>meterStart</code> to set.
     */
    public void setMeterStart(BigInteger meterStart) {
        this.meterStart = meterStart;
    }

    /**
     * Gets <code>meterEnd</code>.
     * @return The <code>meterEnd</code>.
     */
    public BigInteger getMeterEnd() {
        return meterEnd;
    }

    /**
     * Sets <code>meterEnd</code>.
     * @param meterEnd The <code>meterEnd</code> to set.
     */
    public void setMeterEnd(BigInteger meterEnd) {
        this.meterEnd = meterEnd;
    }

    /**
     * Gets <code>bulan</code>.
     * @return The <code>bulan</code>.
     */
    public String getBulan() {
        return bulan;
    }

    /**
     * Sets <code>bulan</code>.
     * @param bulan The <code>bulan</code> to set.
     */
    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    /**
     * Gets <code>totalBill</code>.
     * @return The <code>totalBill</code>.
     */
    public int getTotalBill() {
        return totalBill;
    }

    /**
     * Sets <code>totalBill</code>.
     * @param totalBill The <code>totalBill</code> to set.
     */
    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
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
     * Gets <code>transaction</code>.
     * @return The <code>transaction</code>.
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Sets <code>transaction</code>.
     * @param transaction The <code>transaction</code> to set.
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PLNPostpaid [accountNumber=" + accountNumber + ", amount=" + amount + ", adminFee=" + adminFee
		+ ", penaltyFee=" + penaltyFee + ", tarif=" + tarif + ", daya=" + daya + ", meterStart=" + meterStart
		+ ", meterEnd=" + meterEnd + ", bulan=" + bulan + ", totalBill=" + totalBill + ", totalAmount="
		+ totalAmount + ", transaction=" + transaction + "]";
    }
}
