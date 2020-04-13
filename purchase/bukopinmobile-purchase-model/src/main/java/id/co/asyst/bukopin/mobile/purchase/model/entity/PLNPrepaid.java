/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * Entity Table Model PLN_PREPAID
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 11, 2019
 * @since 1.0.Alpha1
 */
@Entity
@Table(name = "PLN_PREPAID")
public class PLNPrepaid extends IdBasedObject {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 50)
    private String accountNumber;

    @Column(name = "AMOUNT", nullable = false, length = 50)
    private String amount;

    @Column(name = "TARIF", nullable = false, length = 50)
    private String tarif;

    @Column(name = "ADMIN_FEE", nullable = false)
    private BigDecimal adminFee;

    @Column(name = "JUMLAH_KWH", nullable = false, length = 50)
    private String jumlahKwh;

    @Column(name = "DAYA", nullable = false, length = 50)
    private String daya;

    @Column(name = "TOKEN", nullable = false, length = 100)
    private String token;

    @Column(name = "TOTAL_AMOUNT", nullable = true)
    private BigDecimal totalAmount;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_TRANSACTION", referencedColumnName = "id")
    private id.co.asyst.bukopin.mobile.master.model.entity.Transaction transaction;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * Gets <code>transaction</code>.
     * 
     * @return The <code>transaction</code>.
     */
    public id.co.asyst.bukopin.mobile.master.model.entity.Transaction getTransaction() {
	return transaction;
    }

    /**
     * Sets <code>transaction</code>.
     * 
     * @param transaction
     *            The <code>transaction</code> to set.
     */
    public void setTransaction(id.co.asyst.bukopin.mobile.master.model.entity.Transaction transaction) {
	this.transaction = transaction;
    }

    /**
     * Gets <code>amount</code>.
     * 
     * @return The <code>amount</code>.
     */
    public String getAmount() {
	return amount;
    }

    /**
     * Sets <code>amount</code>.
     * 
     * @param amount
     *            The <code>amount</code> to set.
     */
    public void setAmount(String amount) {
	this.amount = amount;
    }

    /**
     * Gets <code>tarif</code>.
     * 
     * @return The <code>tarif</code>.
     */
    public String getTarif() {
	return tarif;
    }

    /**
     * Sets <code>tarif</code>.
     * 
     * @param tarif
     *            The <code>tarif</code> to set.
     */
    public void setTarif(String tarif) {
	this.tarif = tarif;
    }

    /**
     * Gets <code>adminFee</code>.
     * 
     * @return The <code>adminFee</code>.
     */
    public BigDecimal getAdminFee() {
	return adminFee;
    }

    /**
     * Sets <code>adminFee</code>.
     * 
     * @param adminFee
     *            The <code>adminFee</code> to set.
     */
    public void setAdminFee(BigDecimal adminFee) {
	this.adminFee = adminFee;
    }

    /**
     * Gets <code>jumlahKwh</code>.
     * 
     * @return The <code>jumlahKwh</code>.
     */
    public String getJumlahKwh() {
	return jumlahKwh;
    }

    /**
     * Sets <code>jumlahKwh</code>.
     * 
     * @param jumlahKwh
     *            The <code>jumlahKwh</code> to set.
     */
    public void setJumlahKwh(String jumlahKwh) {
	this.jumlahKwh = jumlahKwh;
    }

    /**
     * Gets <code>daya</code>.
     * 
     * @return The <code>daya</code>.
     */
    public String getDaya() {
	return daya;
    }

    /**
     * Sets <code>daya</code>.
     * 
     * @param daya
     *            The <code>daya</code> to set.
     */
    public void setDaya(String daya) {
	this.daya = daya;
    }

    /**
     * Gets <code>token</code>.
     * 
     * @return The <code>token</code>.
     */
    public String getToken() {
	return token;
    }

    /**
     * Sets <code>token</code>.
     * 
     * @param token
     *            The <code>token</code> to set.
     */
    public void setToken(String token) {
	this.token = token;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PLNPrepaid [accountNumber=" + accountNumber + ", amount=" + amount + ", tarif=" + tarif + ", adminFee="
		+ adminFee + ", jumlahKwh=" + jumlahKwh + ", daya=" + daya + ", token=" + token + ", totalAmount="
		+ totalAmount + ", transaction=" + transaction + "]";
    }
}
