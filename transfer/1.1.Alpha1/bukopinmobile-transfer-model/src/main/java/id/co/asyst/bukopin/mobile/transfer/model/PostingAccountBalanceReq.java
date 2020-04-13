/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Inquiry Transaction Response.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 21, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class PostingAccountBalanceReq implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    
    /**
     * effective balance
     */    
    @NotNull(message = "effective balance is Required!")
    @JsonProperty("effectiveBalance")
    private BigDecimal effectiveBalance;
    
    /**
     * hold amount
     */    
    @NotNull(message = "hold amount is Required!")
    @JsonProperty("holdAmmount")
    private BigDecimal holdAmount;
    
    /**
     * available balance
     */ 
    @NotNull(message = "available balance is Required!")
    @JsonProperty("availableBalance")
    private BigDecimal availableBalance;
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public PostingAccountBalanceReq() {
        // do nothing.
    }

    
    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>effectiveBalance</code>.
     * 
     * @return The <code>effectiveBalance</code>.
     */
    public BigDecimal getEffectiveBalance() {
		return effectiveBalance;
	}

    /**
     * Sets <code>effectiveBalance</code>.
     * 
     * @param effectiveBalance
     *            The <code>effectiveBalance</code> to set.
     */
	public void setEffectiveBalance(BigDecimal effectiveBalance) {
		this.effectiveBalance = effectiveBalance;
	}

	/**
     * Gets <code>holdAmmount</code>.
     * 
     * @return The <code>holdAmmount</code>.
     */
	public BigDecimal getHoldAmount() {
		return holdAmount;
	}

	/**
    * Sets <code>holdAmmount</code>.
    * 
    * @param accountType
    *            The <code>holdAmmount</code> to set.
    */
	public void setHoldAmount(BigDecimal holdAmount) {
		this.holdAmount = holdAmount;
	}

	/**
     * Gets <code>availableBalance</code>.
     * 
     * @return The <code>availableBalance</code>.
     */
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	/**
	    * Sets <code>availableBalance</code>.
	    * 
	    * @param availableBalance
	    *            The <code>availableBalance</code> to set.
	    */
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	
	

	/* Functionalities: x */
	

	/* Overrides: */    

	/*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("availableBalance", availableBalance);
        builder.append("effectiveBalance", effectiveBalance);
        builder.append("holdAmount", holdAmount);
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
		PostingAccountBalanceReq other = (PostingAccountBalanceReq) obj;
		if (availableBalance == null) {
			if (other.availableBalance != null)
				return false;
		} else if (!availableBalance.equals(other.availableBalance))
			return false;
		if (effectiveBalance == null) {
			if (other.effectiveBalance != null)
				return false;
		} else if (!effectiveBalance.equals(other.effectiveBalance))
			return false;
		if (holdAmount == null) {
			if (other.holdAmount != null)
				return false;
		} else if (!holdAmount.equals(other.holdAmount))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availableBalance == null) ? 0 : availableBalance.hashCode());
		result = prime * result + ((effectiveBalance == null) ? 0 : effectiveBalance.hashCode());
		result = prime * result + ((holdAmount == null) ? 0 : holdAmount.hashCode());
		return result;
	}
}
