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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
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
 * @version $Revision$, (Created on Nov 21, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class PostingRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    
    /**
     * amount.
     */    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    /**
     * admin fee.
     */  
    @JsonProperty("adminFee")
    private BigDecimal adminFee;
    
    /**
     * method.
     */  
    @JsonProperty("method")
    private String method;
    
    /**
     * message.
     */  
    @JsonProperty("message")
    private String message;
    
    /**
     * posting from.
     */  
    @JsonProperty("from")
    private PostingFromReq postingFrom;
    
    /**
     * posting to.
     */  
    @JsonProperty("to")
    private PostingToReq postingTo;
    
    /**
     * transmission date time.
     */  
    @JsonProperty("transmissionDateTime")
    private String transmissionDateTime;
    
    /**
     * bank reference.
     */  
    @JsonProperty("bankReference")
    private String bankReference;
    
    /**
     * auth id.
     */  
    @JsonProperty("authID")
    private String authID;
    
    /**
     * status code.
     */  
    @JsonProperty("statusCode")
    private String statusCode;
    
    /**
     * status description.
     */  
    @JsonProperty("statusDesc")
    private String statusDesc;
    
    /**
     * stan.
     */  
    @JsonProperty("STAN")
    private String stan;
    
    /**
     * response code.
     */  
    @JsonProperty("responseCode")
    private String responseCode;
    
    /**
     * response description.
     */  
    @JsonProperty("responseDescription")
    private String responseDescription;
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public PostingRes() {
        // do nothing.
    }
    
    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>amount</code>.
     * 
     * @return The <code>amount</code>.
     */
    public BigDecimal getAmount() {
		return amount;
	}

    /**
     * Sets <code>amount</code>.
     * 
     * @param amount
     *            The <code>amount</code> to set.
     */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	/**
     * Gets <code>message</code>.
     * 
     * @return The <code>message</code>.
     */
	public String getMessage() {
		return message;
	}

	/**
     * Sets <code>message</code>.
     * 
     * @param message
     *            The <code>message</code> to set.
     */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
     * Gets <code>postingFrom</code>.
     * 
     * @return The <code>postingFrom</code>.
     */
	public PostingFromReq getPostingFrom() {
		return postingFrom;
	}

	/**
     * Sets <code>postingFrom</code>.
     * 
     * @param postingFrom
     *            The <code>postingFrom</code> to set.
     */
	public void setPostingFrom(PostingFromReq postingFrom) {
		this.postingFrom = postingFrom;
	}

	/**
     * Gets <code>postingTo</code>.
     * 
     * @return The <code>postingTo</code>.
     */
	public PostingToReq getPostingTo() {
		return postingTo;
	}

	/**
     * Sets <code>postingTo</code>.
     * 
     * @param postingTo
     *            The <code>postingTo</code> to set.
     */
	public void setPostingTo(PostingToReq postingTo) {
		this.postingTo = postingTo;
	}

	/**
     * Gets <code>transmissionDateTime</code>.
     * 
     * @return The <code>transmissionDateTime</code>.
     */
	public String getTransmissionDateTime() {
		return transmissionDateTime;
	}

	/**
     * Sets <code>transmissionDateTime</code>.
     * 
     * @param transmissionDateTime
     *            The <code>transmissionDateTime</code> to set.
     */
	public void setTransmissionDateTime(String transmissionDateTime) {
		this.transmissionDateTime = transmissionDateTime;
	}

	/**
     * Gets <code>bankReference</code>.
     * 
     * @return The <code>bankReference</code>.
     */
	public String getBankReference() {
		return bankReference;
	}

	/**
     * Sets <code>bankReference</code>.
     * 
     * @param bankReference
     *            The <code>bankReference</code> to set.
     */
	public void setBankReference(String bankReference) {
		this.bankReference = bankReference;
	}

	/**
     * Gets <code>authID</code>.
     * 
     * @return The <code>authID</code>.
     */
	public String getAuthID() {
		return authID;
	}

	/**
     * Sets <code>authID</code>.
     * 
     * @param authID
     *            The <code>authID</code> to set.
     */
	public void setAuthID(String authID) {
		this.authID = authID;
	}
	
	/**
     * Gets <code>statusCode</code>.
     * 
     * @return The <code>statusCode</code>.
     */
	public String getStatusCode() {
		return statusCode;
	}

	/**
     * Sets <code>statusCode</code>.
     * 
     * @param authID
     *            The <code>statusCode</code> to set.
     */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
     * Gets <code>statusDesc</code>.
     * 
     * @return The <code>statusDesc</code>.
     */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
     * Sets <code>statusDesc</code>.
     * 
     * @param statusDesc
     *            The <code>statusDesc</code> to set.
     */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	/**
     * Gets <code>stan</code>.
     * 
     * @return The <code>stan</code>.
     */
	public String getStan() {
		return stan;
	}

	/**
     * Sets <code>stan</code>.
     * 
     * @param stan
     *            The <code>stan</code> to set.
     */
	public void setStan(String stan) {
		this.stan = stan;
	}
	
	/**
     * Gets <code>response code</code>.
     * 
     * @return The <code>response code</code>.
     */
	public String getResponseCode() {
		return responseCode;
	}

	/**
     * Sets <code>response code</code>.
     * 
     * @param response code
     *            The <code>response code</code> to set.
     */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
     * Gets <code>response description</code>.
     * 
     * @return The <code>response description</code>.
     */
	public String getResponseDescription() {
		return responseDescription;
	}

	/**
     * Sets <code>response description</code>.
     * 
     * @param responseDescription
     *            The <code>responseDescription</code> to set.
     */
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
	
	/**
     * Gets <code>adminFee</code>.
     * 
     * @return The <code>adminFee</code>.
     */
	public BigDecimal getAdminFee() {
		return adminFee;
	}

	/**
     * Sets <code>adminFee</code>.
     * 
     * @param adminFee
     *            The <code>adminFee</code> to set.
     */
	public void setAdminFee(BigDecimal adminFee) {
		this.adminFee = adminFee;
	}
	
	/**
     * Gets <code>method</code>.
     * 
     * @return The <code>method</code>.
     */
	public String getMethod() {
		return method;
	}

	/**
     * Sets <code>method</code>.
     * 
     * @param method
     *            The <code>method</code> to set.
     */
	public void setMethod(String method) {
		this.method = method;
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
        builder.append("amount", amount);
        builder.append("transmissionDateTime", transmissionDateTime);
        builder.append("bankReference", bankReference);
        builder.append("authID", authID);
        builder.append("message", message);
        builder.append("postingFrom", postingFrom);
        builder.append("postingTo", postingTo);
        builder.append("statusCode", statusCode);
        builder.append("statusDesc", statusDesc);
        builder.append("adminFee", adminFee);
        builder.append("method", method);
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
		PostingRes other = (PostingRes) obj;
		if (adminFee == null) {
			if (other.adminFee != null)
				return false;
		} else if (!adminFee.equals(other.adminFee))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (authID == null) {
			if (other.authID != null)
				return false;
		} else if (!authID.equals(other.authID))
			return false;
		if (bankReference == null) {
			if (other.bankReference != null)
				return false;
		} else if (!bankReference.equals(other.bankReference))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (postingFrom == null) {
			if (other.postingFrom != null)
				return false;
		} else if (!postingFrom.equals(other.postingFrom))
			return false;
		if (postingTo == null) {
			if (other.postingTo != null)
				return false;
		} else if (!postingTo.equals(other.postingTo))
			return false;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		if (responseDescription == null) {
			if (other.responseDescription != null)
				return false;
		} else if (!responseDescription.equals(other.responseDescription))
			return false;
		if (stan == null) {
			if (other.stan != null)
				return false;
		} else if (!stan.equals(other.stan))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (statusDesc == null) {
			if (other.statusDesc != null)
				return false;
		} else if (!statusDesc.equals(other.statusDesc))
			return false;
		if (transmissionDateTime == null) {
			if (other.transmissionDateTime != null)
				return false;
		} else if (!transmissionDateTime.equals(other.transmissionDateTime))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((adminFee == null) ? 0 : adminFee.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((authID == null) ? 0 : authID.hashCode());
		result = prime * result
				+ ((bankReference == null) ? 0 : bankReference.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result
				+ ((postingFrom == null) ? 0 : postingFrom.hashCode());
		result = prime * result
				+ ((postingTo == null) ? 0 : postingTo.hashCode());
		result = prime * result
				+ ((responseCode == null) ? 0 : responseCode.hashCode());
		result = prime * result + ((responseDescription == null)
				? 0
				: responseDescription.hashCode());
		result = prime * result + ((stan == null) ? 0 : stan.hashCode());
		result = prime * result
				+ ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result
				+ ((statusDesc == null) ? 0 : statusDesc.hashCode());
		result = prime * result + ((transmissionDateTime == null)
				? 0
				: transmissionDateTime.hashCode());
		return result;
	}
}
