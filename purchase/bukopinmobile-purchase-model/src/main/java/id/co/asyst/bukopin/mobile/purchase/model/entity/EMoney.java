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
 * Entity Table Model Emoney
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 20, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "E_MONEY")
public class EMoney extends IdBasedObject {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */

    /**
     * typeEMoney
     */
    @Column(name = "TYPE_E_MONEY", nullable = false, length = 10)
    private String typeEMoney;

    /**
     * customerNumber
     */
    @Column(name = "CUSTOMER_NUMBER", nullable = false, length = 50)
    private String customerNumber;

    /**
     * amountFee
     */
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    
    /**
     * amountFee
     */
    @Column(name = "AMOUNT_FEE", nullable = false)
    private BigDecimal amountFee;
    
    /**
     * totalAmount
     */
    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;

    /**
     * transaction
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_TRANSACTION", referencedColumnName = "id")
    private id.co.asyst.bukopin.mobile.master.model.entity.Transaction transaction;
    
    /**
     * destination
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_DESTINATION", referencedColumnName = "id")
    private id.co.asyst.bukopin.mobile.master.model.entity.Destination destination;


    /* Transient Attributes: */

    /* Constructors: */
   

    /* Getters & setters for attributes: */

    /**
     * Gets <code>typeEMoney</code>.
     * 
     * @return The <code>typeEMoney</code>.
     */
	public String getTypeEMoney() {
		return typeEMoney;
	}

	/**
     * Sets <code>typeEMoney</code>.
     * 
     * @param typeEMoney
     *            The <code>typeEMoney</code> to set.
     */
	public void setTypeEMoney(String typeEMoney) {
		this.typeEMoney = typeEMoney;
	}

	
    /**
     * Gets <code>customerNumber</code>.
     * 
     * @return The <code>customerNumber</code>.
     */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
     * Sets <code>customerNumber</code>.
     * 
     * @param customerNumber
     *            The <code>customerNumber</code> to set.
     */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	
    /**
     * Gets <code>amount</code>.
     * 
     * @return The <code>amount</code>.
     */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
     * Sets <code>amount</code>.
     * 
     * @param amount
     *            The <code>amount</code> to set.
     */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
    /**
     * Gets <code>amountFee</code>.
     * 
     * @return The <code>amountFee</code>.
     */
	public BigDecimal getAmountFee() {
		return amountFee;
	}

	/**
     * Sets <code>amountFee</code>.
     * 
     * @param amountFee
     *            The <code>amountFee</code> to set.
     */
	public void setAmountFee(BigDecimal amountFee) {
		this.amountFee = amountFee;
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
	public void setTransaction(
			id.co.asyst.bukopin.mobile.master.model.entity.Transaction transaction) {
		this.transaction = transaction;
	}
	
	/**
     * Gets <code>destination</code>.
     * 
     * @return The <code>destination</code>.
     */
	public id.co.asyst.bukopin.mobile.master.model.entity.Destination getDestination() {
		return destination;
	}

	/**
     * Sets <code>destination</code>.
     * 
     * @param transaction
     *            The <code>destination</code> to set.
     */
	public void setDestination(
			id.co.asyst.bukopin.mobile.master.model.entity.Destination destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "EMoney [typeEMoney=" + typeEMoney + ", customerNumber="
				+ customerNumber + ", amount=" + amount + ", amountFee="
				+ amountFee + ", totalAmount=" + totalAmount + ", transaction="
				+ transaction + ", destination=" + destination + "]";
	}
    
    

    
}
