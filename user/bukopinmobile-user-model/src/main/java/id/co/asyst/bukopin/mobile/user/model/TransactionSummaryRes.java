/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Persistent User Remember-Me Token.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class TransactionSummaryRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    
    /**
     * Total Dembit Amount.
     */    
    @JsonProperty("totalDebitAmount")
    private BigDecimal totalDebitAmount;
    
    /**
     * Total Credit Amount.
     */
    @JsonProperty("totalCreditAmount")
    private BigDecimal totalCreditAmount;
   
    /**
     * Ending Balance.
     */
    @JsonProperty("endingBalance")
    private BigDecimal endingBalance;
    
    /**
     * Starting Balance.
     */
    @JsonProperty("startingBalance")
    private BigDecimal startingBalance;
    
    

    

    /* Constructors: */
    /**
     * Constructor.
     */
    public TransactionSummaryRes() {
        // do nothing.
    }

    /* Getters & setters for attributes: */
	
	/**
     * Gets <code>totalDebitAmount</code>.
     * 
     * @return The <code>totalDebitAmount</code>.
     */
	public BigDecimal getTotalDebitAmount() {
		return totalDebitAmount;
	}

	/**
     * Sets <code>totalDebitAmount</code>.
     * 
     * @param totalDebitAmount
     *            The <code>totalDebitAmount</code> to set.
     */
	public void setTotalDebitAmount(BigDecimal totalDebitAmount) {
		this.totalDebitAmount = totalDebitAmount;
	}

	/**
     * Gets <code>totalCreditAmount</code>.
     * 
     * @return The <code>totalCreditAmount</code>.
     */
	public BigDecimal getTotalCreditAmount() {
		return totalCreditAmount;
	}

	/**
     * Sets <code>totalCreditAmount</code>.
     * 
     * @param totalCreditAmount
     *            The <code>totalCreditAmount</code> to set.
     */
	public void setTotalCreditAmount(BigDecimal totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}

	/**
     * Gets <code>endingBalance</code>.
     * 
     * @return The <code>endingBalance</code>.
     */
	public BigDecimal getEndingBalance() {
		return endingBalance;
	}

	/**
     * Sets <code>endingBalance</code>.
     * 
     * @param endingBalance
     *            The <code>endingBalance</code> to set.
     */
	public void setEndingBalance(BigDecimal endingBalance) {
		this.endingBalance = endingBalance;
	}

	/**
     * Gets <code>startingBalance</code>.
     * 
     * @return The <code>startingBalance</code>.
     */
	public BigDecimal getStartingBalance() {
		return startingBalance;
	}

	/**
     * Sets <code>startingBalance</code>.
     * 
     * @param startingBalance
     *            The <code>startingBalance</code> to set.
     */
	public void setStartingBalance(BigDecimal startingBalance) {
		this.startingBalance = startingBalance;
	}

	

	/* Functionalities: x */
	
    

	/*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("totalDebitAmount", totalDebitAmount);
        builder.append("totalCreditAmt", totalCreditAmount);
        builder.append("endingBalance", endingBalance);
        builder.append("startingBalance", startingBalance);
        return builder.toString();
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionSummaryRes other = (TransactionSummaryRes) obj;
		if (endingBalance == null) {
			if (other.endingBalance != null)
				return false;
		} else if (!endingBalance.equals(other.endingBalance))
			return false;
		if (startingBalance == null) {
			if (other.startingBalance != null)
				return false;
		} else if (!startingBalance.equals(other.startingBalance))
			return false;
		if (totalCreditAmount == null) {
			if (other.totalCreditAmount != null)
				return false;
		} else if (!totalCreditAmount.equals(other.totalCreditAmount))
			return false;
		if (totalDebitAmount == null) {
			if (other.totalDebitAmount != null)
				return false;
		} else if (!totalDebitAmount.equals(other.totalDebitAmount))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endingBalance == null) ? 0 : endingBalance.hashCode());
		result = prime * result + ((startingBalance == null) ? 0 : startingBalance.hashCode());
		result = prime * result + ((totalCreditAmount == null) ? 0 : totalCreditAmount.hashCode());
		result = prime * result + ((totalDebitAmount == null) ? 0 : totalDebitAmount.hashCode());
		return result;
	}
}
