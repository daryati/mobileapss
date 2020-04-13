/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
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

import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * Entity Table Model Telco Prepaid
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 19, 2020
 * @since 1.0.Alpha1
 */
@Entity
@Table(name = "TELCO_PREPAID")
public class TelcoPrepaid extends IdBasedObject {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    
    /**
     * accountNumber
     */
    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 50)
    private String accountNumber;
    
    /**
     * typeTelco
     */
    @Column(name = "TYPE_TELCO", nullable = false, length = 50)
    private String typeTelco;
    
    /**
     * amount
     */
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    
    /**
     * adminFee
     */
    @Column(name = "ADMIN_FEE", nullable = false)
    private BigDecimal adminFee;
    
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

	/* Getters & setters for attributes: */
    /**
     * Gets <code>typeTelco</code>.
     * 
     * @return The <code>typeTelco</code>.
     */
	public String getTypeTelco() {
		return typeTelco;
	}

	/**
     * Sets <code>typeTelco</code>.
     * 
     * @param typeTelco
     *            The <code>typeTelco</code> to set.
     */
	public void setTypeTelco(String typeTelco) {
		this.typeTelco = typeTelco;
	}

	
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
     * @param destination
     *            The <code>destination</code> to set.
     */
	public void setDestination(
			id.co.asyst.bukopin.mobile.master.model.entity.Destination destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "TelcoPrepaid [accountNumber=" + accountNumber + ", typeTelco="
				+ typeTelco + ", amount=" + amount + ", adminFee=" + adminFee
				+ ", totalAmount=" + totalAmount + ", transaction="
				+ transaction + ", destination=" + destination + "]";
	}
    
    

    
}
