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
 * Inquiry Transaction Response.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class MiniStatementRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    /**
     * transaction detail.
     */    
    @JsonProperty("transactionDetails")
    private List<MiniStatementTransactionDetailRes> transactionDetails;
    
    /**
     * totalBalance.
     */
    @JsonProperty("accountInfo")
    private AccountInfoRes accountInfo;
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public MiniStatementRes() {
        // do nothing.
    }

    
    /* Getters & setters for attributes: */

    /**
     * Gets <code>transactionDetails</code>.
     * 
     * @return The <code>transactionDetails</code>.
     */
	 public List<MiniStatementTransactionDetailRes> getTransactionDetails() {
		return transactionDetails;
	}

	 /**
	 * Sets <code>transactionDetails</code>.
	 * 
	 * @param transactionDetails
	 *            The <code>transactionDetails</code> to set.
	 */
	public void setTransactionDetails(List<MiniStatementTransactionDetailRes> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	/**
     * Gets <code>accountInfo</code>.
     * 
     * @return The <code>accountInfo</code>.
     */
	public AccountInfoRes getAccountInfo() {
		return accountInfo;
	}

	/**
	 * Sets <code>accountInfo</code>.
	 * 
	 * @param accountInfo
	 *            The <code>accountInfo</code> to set.
	 */
	public void setAccountInfo(AccountInfoRes accountInfo) {
		this.accountInfo = accountInfo;
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
        builder.append("accountInfo", accountInfo);
        builder.append("transactionDetails", transactionDetails);
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
		MiniStatementRes other = (MiniStatementRes) obj;
		if (accountInfo == null) {
			if (other.accountInfo != null)
				return false;
		} else if (!accountInfo.equals(other.accountInfo))
			return false;
		if (transactionDetails == null) {
			if (other.transactionDetails != null)
				return false;
		} else if (!transactionDetails.equals(other.transactionDetails))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountInfo == null) ? 0 : accountInfo.hashCode());
		result = prime * result + ((transactionDetails == null) ? 0 : transactionDetails.hashCode());
		return result;
	}


	
}
