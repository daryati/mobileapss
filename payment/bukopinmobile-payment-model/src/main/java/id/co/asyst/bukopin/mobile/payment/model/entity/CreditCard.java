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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 6, 2020
 * @since 2.0
 */
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard extends IdBasedObject{

    /* Constants: */
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /* Attributes: */
    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    
    @Column(name = "BILLED_AMOUNT")
    private BigDecimal billedAmount;
    
    @Column(name = "MINIMUM_AMOUNT")
    private BigDecimal minimumAmount;
    
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_TRANSACTION", referencedColumnName = "id")
    private Transaction transaction;
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * Gets <code>minimumAmount</code>.
     * @return The <code>minimumAmount</code>.
     */
    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    /**
     * Sets <code>minimumAmount</code>.
     * @param minimumAmount The <code>minimumAmount</code> to set.
     */
    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
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
	return "CreditCard [type=" + type + ", amount=" + amount + ", billedAmount=" + billedAmount + ", minimumAmount="
		+ minimumAmount + ", transaction=" + transaction + "]";
    }
}
