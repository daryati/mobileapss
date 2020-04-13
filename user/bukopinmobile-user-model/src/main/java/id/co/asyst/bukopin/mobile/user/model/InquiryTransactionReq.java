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
import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Persistent User Remember-Me Token.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Oct 31, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class InquiryTransactionReq implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    
    /**
     * date from
     */    
    @JsonProperty("dateFrom")
    private Date dateFrom;
    
    /**
     * date to
     */
    @JsonProperty("dateTo")
    private Date dateTo;
    

    /**
     * account number
     */
    @NotBlank(message ="account number is Required!")
    @JsonProperty("accountNumber")
    private String accountNumber;
   
    /**
     * Account Type.
     */
    @NotNull(message ="account type is Required!")
    @JsonProperty("accountType")
    private BigInteger accountType;
    
    /**
     * Tx Line ()
     */
    @JsonProperty("txLine")
    private BigInteger txLine;

    

    /* Constructors: */
    /**
     * Constructor.
     */
    public InquiryTransactionReq() {
        // do nothing.
    }

    /**
     * Constructor with clientTxId parameter.
     * 
     * @param clientTxId
     *            The clientTxId.
     */
   

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>dateFrom</code>.
     * 
     * @return The <code>dateFrom</code>.
     */
    public Date getDateFrom() {
		return dateFrom;
	}

    /**
     * Sets <code>dateFrom</code>.
     * 
     * @param dateFrom
     *            The <code>dateFrom</code> to set.
     */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	 /**
     * Gets <code>dateTo</code>.
     * 
     * @return The <code>dateTo</code>.
     */
	public Date getDateTo() {
		return dateTo;
	}

	/**
     * Sets <code>dateTo</code>.
     * 
     * @param dateTo
     *            The <code>dateTo</code> to set.
     */
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
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
     * Gets <code>accountType</code>.
     * 
     * @return The <code>accountType</code>.
     */
	public BigInteger getAccountType() {
		return accountType;
	}

	/**
     * Sets <code>accountType</code>.
     * 
     * @param accountType
     *            The <code>accountType</code> to set.
     */
	public void setAccountType(BigInteger accountType) {
		this.accountType = accountType;
	}

	/**
     * Gets <code>txLine</code>.
     * 
     * @return The <code>txLine</code>.
     */
	public BigInteger getTxLine() {
		return txLine;
	}

	/**
     * Sets <code>txLine</code>.
     * 
     * @param txLine
     *            The <code>txLine</code> to set.
     */
	public void setTxLine(BigInteger txLine) {
		this.txLine = txLine;
	}
	
	
	
	

	/* Functionalities: x */
	
	

	/* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObjectAclAware#getId()
     */
   /* @Override
    public Serializable getId() {
        return getId();
    }*/

    /*
     * (non-Javadoc)
     * 
     * @see
     * id.co.asyst.foundation.common.model.BaseObjectAclAware#getUniqueKey()
     */
  /*  @Override
    public Serializable getUniqueKey() {
        return getId();
    }*/

    

	/*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("dateFrom", dateFrom);
        builder.append("dateTo", dateTo);
        builder.append("accountNumber", accountNumber);
        builder.append("accountType", accountType);
        builder.append("txLine", txLine);
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
		InquiryTransactionReq other = (InquiryTransactionReq) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (dateFrom == null) {
			if (other.dateFrom != null)
				return false;
		} else if (!dateFrom.equals(other.dateFrom))
			return false;
		if (dateTo == null) {
			if (other.dateTo != null)
				return false;
		} else if (!dateTo.equals(other.dateTo))
			return false;
		
		if (txLine == null) {
			if (other.txLine != null)
				return false;
		} else if (!txLine.equals(other.txLine))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result + ((txLine == null) ? 0 : txLine.hashCode());
		return result;
	}
}
