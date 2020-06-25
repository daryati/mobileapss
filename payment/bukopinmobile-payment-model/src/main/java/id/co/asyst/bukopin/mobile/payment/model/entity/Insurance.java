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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * List Insurance Entity Model
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 13, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Entity
@Table(name="INSURANCE")
public class Insurance extends IdBasedObject {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 9079370632245838564L;
    
    /* Constants: */

    /* Attributes: */
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    
    @Column(name = "PRE_INSURANCE", nullable = false)
    private BigDecimal preInsurance;
    
    @Column(name = "ADMIN_FEE", nullable = false)
    private BigDecimal adminFee;
    
    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;
    
    @Column(name = "CURRENT_AMOUNT", nullable = false, columnDefinition = "numeric(19,2) default 0")
    private BigDecimal currentAmount;
    
    @Column(name = "MONTH", nullable = false, length = 50)
    private String month;
    
    @Column(name = "PARTICIPANT", nullable = false, length = 50)
    private String participant;
    
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_TRANSACTION", referencedColumnName = "id")
    private id.co.asyst.bukopin.mobile.master.model.entity.Transaction transaction;
    
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_DESTINATION", referencedColumnName = "id")
    private id.co.asyst.bukopin.mobile.master.model.entity.Destination destination;

    /* Constructors: */
    
    /* Getters & setters for attributes: */
    

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
     * Gets <code>preInsurance</code>.
     * @return The <code>preInsurance</code>.
     */
	public BigDecimal getPreInsurance() {
		return preInsurance;
	}

	/**
     * Sets <code>preInsurance</code>.
     * @param preInsurance The <code>preInsurance</code> to set.
     */
	public void setPreInsurance(BigDecimal preInsurance) {
		this.preInsurance = preInsurance;
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
     * Gets <code>month</code>.
     * @return The <code>month</code>.
     */
	public String getMonth() {
		return month;
	}

	/**
     * Sets <code>month</code>.
     * @param month The <code>month</code> to set.
     */
	public void setMonth(String month) {
		this.month = month;
	}


    /**
     * Gets <code>participant</code>.
     * @return The <code>participant</code>.
     */
	public String getParticipant() {
		return participant;
	}

	/**
     * Sets <code>participant</code>.
     * @param participant The <code>participant</code> to set.
     */
	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public id.co.asyst.bukopin.mobile.master.model.entity.Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(
			id.co.asyst.bukopin.mobile.master.model.entity.Transaction transaction) {
		this.transaction = transaction;
	}

	public id.co.asyst.bukopin.mobile.master.model.entity.Destination getDestination() {
		return destination;
	}

	public void setDestination(
			id.co.asyst.bukopin.mobile.master.model.entity.Destination destination) {
		this.destination = destination;
	}

	
	/**
	 * Gets <code>currentAmount</code>.
	 * @return The <code>currentAmount</code>.
	 */
	public BigDecimal getCurrentAmount() {
	    return currentAmount;
	}

	/**
	 * Sets <code>currentAmount</code>.
	 * @param currentAmount The <code>currentAmount</code> to set.
	 */
	public void setCurrentAmount(BigDecimal currentAmount) {
	    this.currentAmount = currentAmount;
	}

	@Override
	public String toString() {
		return "Insurance [amount=" + amount + ", preInsurance=" + preInsurance
				+ ", adminFee=" + adminFee + ", totalAmount=" + totalAmount
				+ ", month=" + month + ", participant=" + participant
				+ ", transaction=" + transaction + "]";
	}

	
	
	
}
