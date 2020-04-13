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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_NULL)
public class AccountBalanceRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    
    /**
     * transaction detail.
     */    
    @JsonProperty("accountDetails")
    private List<AccountBalanceTransactionDetailRes> transactionDetails;
    
    /**
     * totalBalance.
     */
    @JsonProperty("totalBalance")
    private TotalBalanceRes totalBalance;
    
    /**
     * responseCode.
     */
    @JsonProperty("responseCode")
    private String responseCode;
    
    /**
     * responseMessage.
     */
    @JsonProperty("responseMessage")
    private String responseMessage;
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public AccountBalanceRes() {
        // do nothing.
    }


    /* Getters & setters for attributes: */

    /**
     * Gets <code>transactionDetails</code>.
     * 
     * @return The <code>transactionDetails</code>.
     */
	 public List<AccountBalanceTransactionDetailRes> getTransactionDetails() {
		return transactionDetails;
	}

	 /**
	     * Sets <code>transactionDetails</code>.
	     * 
	     * @param transactionDetails
	     *            The <code>transactionDetails</code> to set.
	     */
	public void setTransactionDetails(List<AccountBalanceTransactionDetailRes> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	/**
     * Gets <code>totalBalance</code>.
     * 
     * @return The <code>totalBalance</code>.
     */
	public TotalBalanceRes getTotalBalance() {
		return totalBalance;
	}

	/**
     * Sets <code>totalBalance</code>.
     * 
     * @param totalBalance
     *            The <code>totalBalance</code> to set.
     */
	public void setTotalBalance(TotalBalanceRes totalBalance) {
		this.totalBalance = totalBalance;
	}

	/**
     * Gets <code>responseCode</code>.
     * 
     * @return The <code>responseCode</code>.
     */
	public String getResponseCode() {
		return responseCode;
	}


	/**
     * Sets <code>responseCode</code>.
     * 
     * @param responseCode
     *            The <code>responseCode</code> to set.
     */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	/**
     * Gets <code>responseMessage</code>.
     * 
     * @return The <code>responseMessage</code>.
     */
	public String getResponseMessage() {
		return responseMessage;
	}


	/**
     * Sets <code>responseMessage</code>.
     * 
     * @param responseMessage
     *            The <code>responseMessage</code> to set.
     */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
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
        builder.append("totalBalance", totalBalance);
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
		AccountBalanceRes other = (AccountBalanceRes) obj;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		if (responseMessage == null) {
			if (other.responseMessage != null)
				return false;
		} else if (!responseMessage.equals(other.responseMessage))
			return false;
		if (totalBalance == null) {
			if (other.totalBalance != null)
				return false;
		} else if (!totalBalance.equals(other.totalBalance))
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
		result = prime * result
				+ ((responseCode == null) ? 0 : responseCode.hashCode());
		result = prime * result
				+ ((responseMessage == null) ? 0 : responseMessage.hashCode());
		result = prime * result
				+ ((totalBalance == null) ? 0 : totalBalance.hashCode());
		result = prime * result + ((transactionDetails == null)
				? 0
				: transactionDetails.hashCode());
		return result;
	}
}
