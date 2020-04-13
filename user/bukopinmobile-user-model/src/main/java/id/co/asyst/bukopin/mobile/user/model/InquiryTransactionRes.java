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

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Inquiry Transaction Response.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class InquiryTransactionRes implements Serializable {

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
    private String accountType;
   
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
    private String cifNumber;
    
    /**
     * CIF Status.
     */
    @JsonProperty("cifStatus")
    private CIFStatusEnum cifStatus;
    
    /**
     * Transaction Summary.
     */
    @JsonProperty("transactionSummary")
    private TransactionSummaryRes transactionSummary;
    
    /**
     * List Transaction Detail .
     */
    @JsonProperty("transactionDetails")
    private List<InquiryTransactionDetailRes> transactiondetails;
    
    /**
     * response code.
     */
    @JsonProperty("responseCode")
    private String responseCode;
    
    /**
     * response message.
     */
    @JsonProperty("responseMessage")
    private String responseMessage;
    
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public InquiryTransactionRes() {
        // do nothing.
    }

    /**
     * Constructor with clientTxId parameter.
     * 
     * @param clientTxId
     *            The clientTxId.
     */
   /* public InquiryTransactionRes(String accountNumber) {
        super();
        this.accountNumber = accountNumber;
    }*/

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
     * Gets <code>clientTxId</code>.
     * 
     * @return The <code>clientTxId</code>.
     */
	public String getCurrency() {
		return currency;
	}

	 /**
     * Sets <code>clientTxId</code>.
     * 
     * @param clientTxId
     *            The <code>clientTxId</code> to set.
     */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
     * Gets <code>clientTxId</code>.
     * 
     * @return The <code>clientTxId</code>.
     */
	public String getBranch() {
		return branch;
	}

	 /**
     * Sets <code>clientTxId</code>.
     * 
     * @param clientTxId
     *            The <code>clientTxId</code> to set.
     */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
     * Gets <code>clientTxId</code>.
     * 
     * @return The <code>clientTxId</code>.
     */
	public String getLocation() {
		return location;
	}

	 /**
     * Sets <code>clientTxId</code>.
     * 
     * @param clientTxId
     *            The <code>clientTxId</code> to set.
     */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
     * Gets <code>clientTxId</code>.
     * 
     * @return The <code>clientTxId</code>.
     */
	public String getProductID() {
		return productID;
	}

	 /**
     * Sets <code>clientTxId</code>.
     * 
     * @param clientTxId
     *            The <code>clientTxId</code> to set.
     */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
     * Gets <code>clientTxId</code>.
     * 
     * @return The <code>clientTxId</code>.
     */
	public String getCifNumber() {
		return cifNumber;
	}

	 /**
     * Sets <code>clientTxId</code>.
     * 
     * @param clientTxId
     *            The <code>clientTxId</code> to set.
     */
	public void setCifNumber(String cifNumber) {
		this.cifNumber = cifNumber;
	}

	/**
     * Gets <code>clientTxId</code>.
     * 
     * @return The <code>clientTxId</code>.
     */
	public CIFStatusEnum getCifStatus() {
		return cifStatus;
	}

	 /**
     * Sets <code>clientTxId</code>.
     * 
     * @param clientTxId
     *            The <code>clientTxId</code> to set.
     */
	public void setCifStatus(CIFStatusEnum cifStatus) {
		this.cifStatus = cifStatus;
	}

	/**
     * Gets <code>transactionSummary</code>.
     * 
     * @return The <code>transactionSummary</code>.
     */
	public TransactionSummaryRes getTransactionSummary() {
		return transactionSummary;
	}

	/**
     * Sets <code>transactionSummary</code>.
     * 
     * @param transactionSummary
     *            The <code>transactionSummary</code> to set.
     */
	public void setTransactionSummary(TransactionSummaryRes transactionSummary) {
		this.transactionSummary = transactionSummary;
	}

	/**
     * Gets <code>transactiondetails</code>.
     * 
     * @return The <code>transactiondetails</code>.
     */
	public List<InquiryTransactionDetailRes> getTransactiondetails() {
		return transactiondetails;
	}

	/**
     * Sets <code>transactiondetails</code>.
     * 
     * @param transactiondetails
     *            The <code>transactiondetails</code> to set.
     */
	public void setTransactiondetails(List<InquiryTransactionDetailRes> transactiondetails) {
		this.transactiondetails = transactiondetails;
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
/*    @Override
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
		InquiryTransactionRes other = (InquiryTransactionRes) obj;
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
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
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
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((cifNumber == null) ? 0 : cifNumber.hashCode());
		result = prime * result + ((cifStatus == null) ? 0 : cifStatus.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productID == null) ? 0 : productID.hashCode());
		return result;
	}
}
