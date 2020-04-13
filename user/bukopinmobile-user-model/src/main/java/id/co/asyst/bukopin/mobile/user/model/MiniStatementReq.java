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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Persistent User Remember-Me Token.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 05, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class MiniStatementReq extends BaseObjectAclAware implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    /**
     * Account Number
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
    
    

    

    /* Constructors: */
    /**
     * Constructor.
     */
    public MiniStatementReq() {
        // do nothing.
    }


    /* Getters & setters for attributes: */


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

	

	/* Functionalities: x */
	
	

	/* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObjectAclAware#getId()
     */
    @Override
    public Serializable getId() {
        return getId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * id.co.asyst.foundation.common.model.BaseObjectAclAware#getUniqueKey()
     */
    @Override
    public Serializable getUniqueKey() {
        return getAccountNumber();
    }

    

	/*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("accountNumber", accountNumber);
        builder.append("accountType", accountType);
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
		MiniStatementReq other = (MiniStatementReq) obj;
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
		
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		return result;
	}
}
