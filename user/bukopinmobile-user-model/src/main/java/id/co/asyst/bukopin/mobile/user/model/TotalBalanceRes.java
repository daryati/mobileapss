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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Persistent User Remember-Me Token.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalBalanceRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    /**
     * Total Dembit Amount.
     */    
    @JsonProperty("totalEffectiveBalance")
    private BigDecimal totalEffectiveBalance;
    
    /**
     * Total Credit Amount.
     */
    @JsonProperty("totalAvailableBalance")
    private BigDecimal totalAvailableBalance;
   
   

    /* Constructors: */
    /**
     * Constructor.
     */
    public TotalBalanceRes() {
        // do nothing.
    }


    /* Getters & setters for attributes: */
	
    
	/**
     * Gets <code>totalEffectiveBalance</code>.
     * 
     * @return The <code>totalEffectiveBalance</code>.
     */

	public BigDecimal getTotalEffectiveBalance() {
		return totalEffectiveBalance;
	}

	/**
     * Sets <code>totalEffectiveBalance</code>.
     * 
     * @param totalEffectiveBalance
     *            The <code>totalEffectiveBalance</code> to set.
     */
	public void setTotalEffectiveBalance(BigDecimal totalEffectiveBalance) {
		this.totalEffectiveBalance = totalEffectiveBalance;
	}

	/**
     * Gets <code>totalAvailableBalance</code>.
     * 
     * @return The <code>totalAvailableBalance</code>.
     */
	public BigDecimal getTotalAvailableBalance() {
		return totalAvailableBalance;
	}

	/**
     * Sets <code>totalAvailableBalance</code>.
     * 
     * @param totalAvailableBalance
     *            The <code>totalAvailableBalance</code> to set.
     */
	public void setTotalAvailableBalance(BigDecimal totalAvailableBalance) {
		this.totalAvailableBalance = totalAvailableBalance;
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
        builder.append("totalEffectiveBalance", totalEffectiveBalance);
        builder.append("totalAvailableBalance", totalAvailableBalance);
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
		TotalBalanceRes other = (TotalBalanceRes) obj;
		
		if (totalAvailableBalance == null) {
			if (other.totalAvailableBalance != null)
				return false;
		} else if (!totalAvailableBalance.equals(other.totalAvailableBalance))
			return false;
		if (totalEffectiveBalance == null) {
			if (other.totalEffectiveBalance != null)
				return false;
		} else if (!totalEffectiveBalance.equals(other.totalEffectiveBalance))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((totalAvailableBalance == null) ? 0 : totalAvailableBalance.hashCode());
		result = prime * result + ((totalEffectiveBalance == null) ? 0 : totalEffectiveBalance.hashCode());
		return result;
	}
}
