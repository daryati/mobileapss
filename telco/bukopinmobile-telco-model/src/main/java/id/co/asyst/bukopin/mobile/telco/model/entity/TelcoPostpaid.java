/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 20, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
@Entity
@Table(name = "TELCO_POSTPAID")
public class TelcoPostpaid extends IdBasedObject{

    /**
     * 
     */
    private static final long serialVersionUID = -7556496835139478237L;
    /* Constants: */

    /* Attributes: */
    @Column(name = "CUSTOMER_NO", nullable = false)
    private String customerNo;
    
    @Column(name = "CUSTOMER_NAME", nullable = false, length = 50)
    private String customerName;
    
    @Column(name = "PERIODE", length = 20)
    private String periode;
    
    @Column(name = "PRODUCT_NAME", length = 20)
    private String productName;
    
    @Column(name = "TYPE", length = 20)
    private String type;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    
    @Column(name = "AMOUNT_FEE")
    private BigDecimal amountFee;
    
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_TRANSACTION", referencedColumnName = "id")
    private Transaction idTransaction;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>customerNo</code>.
     * @return The <code>customerNo</code>.
     */
    public String getCustomerNo() {
        return customerNo;
    }

    /**
     * Sets <code>customerNo</code>.
     * @param customerNo The <code>customerNo</code> to set.
     */
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    /**
     * Gets <code>customerName</code>.
     * @return The <code>customerName</code>.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets <code>customerName</code>.
     * @param customerName The <code>customerName</code> to set.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets <code>periode</code>.
     * @return The <code>periode</code>.
     */
    public String getPeriode() {
        return periode;
    }

    /**
     * Sets <code>periode</code>.
     * @param periode The <code>periode</code> to set.
     */
    public void setPeriode(String periode) {
        this.periode = periode;
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
     * Gets <code>idTransaction</code>.
     * @return The <code>idTransaction</code>.
     */
    public Transaction getIdTransaction() {
        return idTransaction;
    }

    /**
     * Sets <code>idTransaction</code>.
     * @param idTransaction The <code>idTransaction</code> to set.
     */
    public void setIdTransaction(Transaction idTransaction) {
        this.idTransaction = idTransaction;
    }

    /**
     * Gets <code>serialversionuid</code>.
     * @return The <code>serialversionuid</code>.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelcoPostpaid [customerNo=" + customerNo + ", customerName=" + customerName + ", periode=" + periode
		+ ", productName=" + productName + ", type=" + type + ", amount=" + amount + ", amountFee=" + amountFee
		+ ", totalAmount=" + totalAmount + ", idTransaction=" + idTransaction + "]";
    }

}
