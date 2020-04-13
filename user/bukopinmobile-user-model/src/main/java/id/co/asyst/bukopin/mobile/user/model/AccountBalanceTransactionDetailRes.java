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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Inquiry Transaction Response.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBalanceTransactionDetailRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    
    /**
     * accountNumber.
     */    
    @JsonProperty("accountNumber")
    private String accountNumber;
    
    /**
     * accountType.
     */
    @JsonProperty("accountType")
    private BigInteger accountType;
   
    /**
     * Name.
     */
    @JsonProperty("name")
    private String name;
    
    /**
     * Account Status.
     */
    @JsonProperty("accountStatus")
    private CIFStatusEnum accountStatus;

    /**
     *Currency.
     */
    @JsonProperty("currency")
    private String currency;
    
    /**
     * Branch.
     */
    @JsonProperty("branch")
    private String branch;
    
    /**
     * Location.
     */
    @JsonProperty("location")
    private String location;
    
    /**
     * Product ID.
     */
    @JsonProperty("productID")
    private String productID;
    
    /**
     * CIF Number.
     */
    @JsonProperty("cifNumber")
    private BigInteger cifNumber;
    
    /**
     * CIF Status.
     */
    @JsonProperty("cifStatus")
    private CIFStatusEnum cifStatus;
    
    /**
     * effective balance.
     */
    @JsonProperty("effectiveBalance")
    private BigDecimal effectiveBalance;
    
    /**
     * hold amount.
     */
    @JsonProperty("holdAmount")
    private BigDecimal holdAmount;
    
    /**
     * available balance.
     */
    @JsonProperty("availableBalance")
    private BigDecimal availableBalance;
    
    /**
     * plafon.
     */
    @JsonProperty("plafon")
    private BigDecimal plafon;
    
    /**
     * list of transaction detail.
     */
    @JsonProperty("tranasctionDetail")
    private List<InquiryTransactionDetailRes> transactionDetails;
    
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public AccountBalanceTransactionDetailRes() {
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
     * Gets <code>accountStatus</code>.
     * 
     * @return The <code>accountStatus</code>.
     */
	public CIFStatusEnum getAccountStatus() {
		return accountStatus;
	}

	 /**
     * Sets <code>accountStatus</code>.
     * 
     * @param accountStatus
     *            The <code>accountStatus</code> to set.
     */
	public void setAccountStatus(CIFStatusEnum accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
     * Gets <code>currency</code>.
     * 
     * @return The <code>currency</code>.
     */
	public String getCurrency() {
		return currency;
	}

	 /**
     * Sets <code>currency</code>.
     * 
     * @param currency
     *            The <code>currency</code> to set.
     */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
     * Gets <code>branch</code>.
     * 
     * @return The <code>branch</code>.
     */
	public String getBranch() {
		return branch;
	}

	 /**
     * Sets <code>branch</code>.
     * 
     * @param branch
     *            The <code>branch</code> to set.
     */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
     * Gets <code>location</code>.
     * 
     * @return The <code>location</code>.
     */
	public String getLocation() {
		return location;
	}

	 /**
     * Sets <code>location</code>.
     * 
     * @param location
     *            The <code>location</code> to set.
     */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
     * Gets <code>productID</code>.
     * 
     * @return The <code>productID</code>.
     */
	public String getProductID() {
		return productID;
	}

	 /**
     * Sets <code>productID</code>.
     * 
     * @param productID
     *            The <code>productID</code> to set.
     */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
     * Gets <code>cifNumber</code>.
     * 
     * @return The <code>cifNumber</code>.
     */
	public BigInteger getCifNumber() {
		return cifNumber;
	}

	 /**
     * Sets <code>cifNumber</code>.
     * 
     * @param cifNumber
     *            The <code>cifNumber</code> to set.
     */
	public void setCifNumber(BigInteger cifNumber) {
		this.cifNumber = cifNumber;
	}

	/**
     * Gets <code>cifStatus</code>.
     * 
     * @return The <code>cifStatus</code>.
     */
	public CIFStatusEnum getCifStatus() {
		return cifStatus;
	}

	 /**
     * Sets <code>cifStatus</code>.
     * 
     * @param cifStatus
     *            The <code>cifStatus</code> to set.
     */
	public void setCifStatus(CIFStatusEnum cifStatus) {
		this.cifStatus = cifStatus;
	}

	
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
     * Gets <code>holdAmount</code>.
     * 
     * @return The <code>holdAmount</code>.
     */
	public BigDecimal getHoldAmount() {
		return holdAmount;
	}

	/**
     * Sets <code>holdAmount</code>.
     * 
     * @param holdAmount
     *            The <code>holdAmount</code> to set.
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

	/**
     * Gets <code>plafon</code>.
     * 
     * @return The <code>plafon</code>.
     */
	public BigDecimal getPlafon() {
		return plafon;
	}

	/**
     * Sets <code>plafon</code>.
     * 
     * @param plafon
     *            The <code>plafon</code> to set.
     */
	public void setPlafon(BigDecimal plafon) {
		this.plafon = plafon;
	}
	
	

	/**
     * Gets <code>transactionDetails</code>.
     * 
     * @return The <code>transactionDetails</code>.
     */
	public List<InquiryTransactionDetailRes> getTransactionDetails() {
		return transactionDetails;
	}


	/**
     * Sets <code>transactionDetails</code>.
     * 
     * @param transactionDetails
     *            The <code>transactionDetails</code> to set.
     */
	public void setTransactionDetails(List<InquiryTransactionDetailRes> transactionDetails) {
		this.transactionDetails = transactionDetails;
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
        builder.append("accountNumber", accountNumber);
        builder.append("accountType", accountType);
        builder.append("name", name);
        builder.append("accountStatus", accountStatus);
        builder.append("currency", currency);
        builder.append("branch", branch);
        builder.append("location", location);
        builder.append("productID", productID);
        builder.append("cifNumber", cifNumber);
        builder.append("cifStatus", cifStatus);
        builder.append("effectiveBalance", effectiveBalance);
        builder.append("holdAmount", holdAmount);
        builder.append("availableBalance", availableBalance);
        builder.append("plafon", plafon);
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
		AccountBalanceTransactionDetailRes other = (AccountBalanceTransactionDetailRes) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountStatus == null) {
			if (other.accountStatus != null)
				return false;
		} else if (!accountStatus.equals(other.accountStatus))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (availableBalance == null) {
			if (other.availableBalance != null)
				return false;
		} else if (!availableBalance.equals(other.availableBalance))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (cifNumber == null) {
			if (other.cifNumber != null)
				return false;
		} else if (!cifNumber.equals(other.cifNumber))
			return false;
		if (cifStatus == null) {
			if (other.cifStatus != null)
				return false;
		} else if (!cifStatus.equals(other.cifStatus))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
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
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (plafon == null) {
			if (other.plafon != null)
				return false;
		} else if (!plafon.equals(other.plafon))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
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
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((availableBalance == null) ? 0 : availableBalance.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((cifNumber == null) ? 0 : cifNumber.hashCode());
		result = prime * result + ((cifStatus == null) ? 0 : cifStatus.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((effectiveBalance == null) ? 0 : effectiveBalance.hashCode());
		result = prime * result + ((holdAmount == null) ? 0 : holdAmount.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((plafon == null) ? 0 : plafon.hashCode());
		result = prime * result + ((productID == null) ? 0 : productID.hashCode());
		result = prime * result + ((transactionDetails == null) ? 0 : transactionDetails.hashCode());
		return result;
	}
}
