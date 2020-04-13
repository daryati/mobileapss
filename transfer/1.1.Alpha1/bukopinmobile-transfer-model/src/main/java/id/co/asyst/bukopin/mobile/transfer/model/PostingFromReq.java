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

public class PostingFromReq implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    
    /**
     * accountNumber.
     */    
    @NotNull(message = "account number is Required!")
    @JsonProperty("accountNumber")
    private BigInteger accountNumber;
    
    /**
     * username.
     */   
    @NotBlank(message = "username is Required!")
    @JsonProperty("username")
    private String username;
    
    /**
     * account name.
     */   
    @NotBlank(message = "account name is Required!")
    @JsonProperty("accountName")
    private String accountName;
    
    /**
     * account type.
     */   
    @NotBlank(message = "account type is Required!")
    @JsonProperty("accountType")
    private String accountType;
    
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public PostingFromReq() {
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
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
	public String getUsername() {
		return username;
	}

	/**
     * Sets <code>username</code>.
     * 
     * @param accountType
     *            The <code>username</code> to set.
     */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
     * Gets <code>accountType</code>.
     * 
     * @return The <code>accountType</code>.
     */
	public String getAccountType() {
		return accountType;
	}

	/**
     * Sets <code>accountType</code>.
     * 
     * @param accountType
     *            The <code>accountType</code> to set.
     */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
        builder.append("accountType", accountType);
        builder.append("username", username);
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
		PostingFromReq other = (PostingFromReq) obj;
		
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
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
}
