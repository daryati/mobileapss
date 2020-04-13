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

public class InquiryTransactionDetailRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    /**
     * Posting Date.
     */    
    @JsonProperty("postingDate")
    private String postingDate;
    
    /**
     * Effective Date.
     */
    @JsonProperty("effectiveDate")
    private String effectiveDate;
   
    /**
     * Description.
     */
    @JsonProperty("description")
    private String description;
    
    /**
     * Amount Debit.
     */
    @JsonProperty("amountDebit")
    private BigDecimal amountDebit;
    
    /**
     * Amount Credit.
     */
    @JsonProperty("amountCredit")
    private BigDecimal amountCredit;
    
    /**
     * Amount Debit.
     */
    @JsonProperty("balanceAmount")
    private BigDecimal balanceAmount;
    
    /**
     * Reff Number.
     */
    @JsonProperty("reffNumber")
    private String reffNumber;

    

    /* Constructors: */
    /**
     * Constructor.
     */
    public InquiryTransactionDetailRes() {
        // do nothing.
    }

    

    /* Getters & setters for attributes: */

	
	/**
     * Gets <code>postingDate</code>.
     * 
     * @return The <code>postingDate</code>.
     */
	public String getPostingDate() {
		return postingDate;
	}

	/**
     * Sets <code>postingDate</code>.
     * 
     * @param postingDate
     *            The <code>postingDate</code> to set.
     */
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	/**
     * Gets <code>effectiveDate</code>.
     * 
     * @return The <code>effectiveDate</code>.
     */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
     * Sets <code>effectiveDate</code>.
     * 
     * @param effectiveDate
     *            The <code>effectiveDate</code> to set.
     */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
     * Gets <code>description</code>.
     * 
     * @return The <code>description</code>.
     */
	public String getDescription() {
		return description;
	}

	/**
     * Sets <code>description</code>.
     * 
     * @param description
     *            The <code>description</code> to set.
     */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
     * Gets <code>amountDebit</code>.
     * 
     * @return The <code>amountDebit</code>.
     */
	public BigDecimal getAmountDebit() {
		return amountDebit;
	}

	/**
     * Sets <code>amountDebit</code>.
     * 
     * @param amountDebit
     *            The <code>amountDebit</code> to set.
     */
	public void setAmountDebit(BigDecimal amountDebit) {
		this.amountDebit = amountDebit;
	}

	/**
     * Gets <code>amountCredit</code>.
     * 
     * @return The <code>amountCredit</code>.
     */
	public BigDecimal getAmountCredit() {
		return amountCredit;
	}

	/**
     * Sets <code>amountCredit</code>.
     * 
     * @param amountCredit
     *            The <code>amountCredit</code> to set.
     */
	public void setAmountCredit(BigDecimal amountCredit) {
		this.amountCredit = amountCredit;
	}

	/**
     * Gets <code>balanceAmount</code>.
     * 
     * @return The <code>balanceAmount</code>.
     */
	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	/**
     * Sets <code>balanceAmount</code>.
     * 
     * @param balanceAmount
     *            The <code>balanceAmount</code> to set.
     */
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
     * Gets <code>reffNumber</code>.
     * 
     * @return The <code>reffNumber</code>.
     */
	public String getReffNumber() {
		return reffNumber;
	}

	/**
     * Sets <code>reffNumber</code>.
     * 
     * @param reffNumber
     *            The <code>reffNumber</code> to set.
     */
	public void setReffNumber(String reffNumber) {
		this.reffNumber = reffNumber;
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
        builder.append("postingDate", postingDate);
        builder.append("effectiveDate", effectiveDate);
        builder.append("amountDebit", amountDebit);
        builder.append("amountCredit", amountCredit);
        builder.append("balanceAmount", balanceAmount);
        builder.append("reffNumber", reffNumber);
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
		InquiryTransactionDetailRes other = (InquiryTransactionDetailRes) obj;
		if (amountCredit == null) {
			if (other.amountCredit != null)
				return false;
		} else if (!amountCredit.equals(other.amountCredit))
			return false;
		if (amountDebit == null) {
			if (other.amountDebit != null)
				return false;
		} else if (!amountDebit.equals(other.amountDebit))
			return false;
		if (balanceAmount == null) {
			if (other.balanceAmount != null)
				return false;
		} else if (!balanceAmount.equals(other.balanceAmount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		
		if (postingDate == null) {
			if (other.postingDate != null)
				return false;
		} else if (!postingDate.equals(other.postingDate))
			return false;
		if (reffNumber == null) {
			if (other.reffNumber != null)
				return false;
		} else if (!reffNumber.equals(other.reffNumber))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountCredit == null) ? 0 : amountCredit.hashCode());
		result = prime * result + ((amountDebit == null) ? 0 : amountDebit.hashCode());
		result = prime * result + ((balanceAmount == null) ? 0 : balanceAmount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((postingDate == null) ? 0 : postingDate.hashCode());
		result = prime * result + ((reffNumber == null) ? 0 : reffNumber.hashCode());
		return result;
	}
}
