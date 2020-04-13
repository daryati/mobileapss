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
import javax.validation.constraints.NotBlank;
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

public class PostingToReq implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */  
    /**
     * account number.
     */    
    @NotNull(message = "account number is Required!")
    @JsonProperty("accountNumber")
    private BigInteger accountNumber;
    
    /**
     * account name.
     */ 
    @NotBlank(message = "account name is Required!")
    @JsonProperty("accountName")
    private String accountName;
    
    /**
     * alias.
     */ 
    @NotBlank(message = "alias is Required!")
    @JsonProperty("alias")
    private String alias;
    
    /**
     * bank code.
     */ 
    @NotBlank(message = "bank code is Required!")
    @JsonProperty("bankCode")
    private String bankCode;
    
    /**
     * is save.
     */ 
    @JsonProperty("isSave")
    private boolean isSave;
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public PostingToReq() {
        // do nothing.
    }
    
    
    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>accountNumber</code>.
     * 
     * @return The <code>accountNumber</code>.
     */
	public BigInteger getAccountNumber() {
		return accountNumber;
	}

	/**
     * Sets <code>accountNumber</code>.
     * 
     * @param accountNumber
     *            The <code>accountNumber</code> to set.
     */
	public void setAccountNumber(BigInteger accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
     * Gets <code>accountName</code>.
     * 
     * @return The <code>accountName</code>.
     */
	public String getAccountName() {
		return accountName;
	}

	/**
     * Sets <code>accountName</code>.
     * 
     * @param accountName
     *            The <code>accountName</code> to set.
     */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	

	/**
     * Gets <code>alias</code>.
     * 
     * @return The <code>alias</code>.
     */
	public String getAlias() {
		return alias;
	}


	/**
     * Sets <code>alias</code>.
     * 
     * @param alias
     *            The <code>alias</code> to set.
     */
	public void setAlias(String alias) {
		this.alias = alias;
	}


	/**
     * Gets <code>bankCode</code>.
     * 
     * @return The <code>bankCode</code>.
     */
	public String getBankCode() {
		return bankCode;
	}

	/**
     * Gets <code>is save</code>.
     * 
     * @return The <code>isSave</code>.
     */
	public boolean isSave() {
	    return isSave;
	}


	/**
     * Sets <code>save</code>.
     * 
     * @param save
     *            The <code>save</code> to set.
     */
	public void setSave(boolean isSave) {
	    this.isSave = isSave;
	}

	/**
     * Sets <code>bankCode</code>.
     * 
     * @param bankCode
     *            The <code>bankCode</code> to set.
     */
	public void setBankCode(String bankCode) {
	    this.bankCode = bankCode;
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
        builder.append("accountNumber", accountNumber);
        builder.append("alias", alias);
        builder.append("bankCode", bankCode);
        builder.append("accountName", accountName);
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
		PostingToReq other = (PostingToReq) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (bankCode == null) {
			if (other.bankCode != null)
				return false;
		} else if (!bankCode.equals(other.bankCode))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
		return result;
	}
}
