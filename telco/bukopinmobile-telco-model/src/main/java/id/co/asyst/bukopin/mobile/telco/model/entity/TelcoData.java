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
 * Entity Table Model Telco Data
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Aug 07, 2020
 * @since 1.0.Alpha1
 */
@Entity
@Table(name = "TELCO_DATA")
public class TelcoData extends IdBasedObject {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    
    
    /**
     * typeData
     */
    @Column(name = "TYPE_DATA", nullable = false, length = 50)
    private String typeData;
    
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
     * title
     */
    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;
    
   
    /**
     * transaction
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_TRANSACTION", referencedColumnName = "id")
    private id.co.asyst.bukopin.mobile.master.model.entity.Transaction transaction;
    
  /*  *//**
     * destination
     *//*
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_DESTINATION", referencedColumnName = "id")
    private id.co.asyst.bukopin.mobile.master.model.entity.Destination destination;*/


    /* Transient Attributes: */

    /* Constructors: */
   

    /* Getters & setters for attributes: */

	/* Getters & setters for attributes: */
    /**
     * Gets <code>typeData</code>.
     * 
     * @return The <code>typeData</code>.
     */
	public String getTypeData() {
		return typeData;
	}

	/**
     * Sets <code>typeData</code>.
     * 
     * @param typeData
     *            The <code>typeData</code> to set.
     */
	public void setTypeData(String typeData) {
		this.typeData = typeData;
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
     * Gets <code>title</code>.
     * 
     * @return The <code>title</code>.
     */
	public String getTitle() {
		return title;
	}

	/**
     * Sets <code>title</code>.
     * 
     * @param title
     *            The <code>title</code> to set.
     */
	public void setTitle(String title) {
		this.title = title;
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
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TelcoData [typeData=" + typeData + ", amount=" + amount + ", adminFee=" + adminFee + ", title=" + title
				+ ", transaction=" + transaction + "]";
	}
    
    

    
}
