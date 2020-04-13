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
import java.util.List;

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

import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceResType.TotalBalance;
import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Account Info Response.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 05, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class AccountInfoRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    /**
     * list of accountNumber.
     */
    @JsonProperty("accountNumber")
    private String accountNumber;
    
    /**
     * name.
     */    
    @JsonProperty("name")
    private String name;
    
    /**
     * last balance.
     */    
    @JsonProperty("lastBalance")
    private BigDecimal lastBalance;
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public AccountInfoRes() {
        // do nothing.
    }

    

    /* Getters & setters for attributes: */    

	/**
     * Sets <code>accountNumbers</code>.
     * 
     * @param accountNumbers
     *            The <code>accountNumbers</code> to set.
     */
    public String getAccountNumber() {
		return accountNumber;
	}


    /**
     * Sets <code>accountNumbers</code>.
     * 
     * @param accountNumbers
     *            The <code>accountNumbers</code> to set.
     */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	/**
     * Gets <code>name</code>.
     * 
     * @return The <code>name</code>.
     */	
	public String getName() {
		return name;
	}


	/**
     * Sets <code>name</code>.
     * 
     * @param name
     *            The <code>name</code> to set.
     */
	public void setName(String name) {
		this.name = name;
	}


	/**
     * Gets <code>lastBalance</code>.
     * 
     * @return The <code>lastBalance</code>.
     */	
	public BigDecimal getLastBalance() {
		return lastBalance;
	}


	/**
     * Sets <code>lastBalance</code>.
     * 
     * @param lastBalance
     *            The <code>lastBalance</code> to set.
     */
	public void setLastBalance(BigDecimal lastBalance) {
		this.lastBalance = lastBalance;
	}

	/* Functionalities: x */
	
	

	/* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObjectAclAware#getId()
     */
	


    

	/*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("name", name);
        builder.append("lastBalance", lastBalance);
        builder.append("accountNumber", accountNumber);
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
		AccountInfoRes other = (AccountInfoRes) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (lastBalance == null) {
			if (other.lastBalance != null)
				return false;
		} else if (!lastBalance.equals(other.lastBalance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((lastBalance == null) ? 0 : lastBalance.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}
