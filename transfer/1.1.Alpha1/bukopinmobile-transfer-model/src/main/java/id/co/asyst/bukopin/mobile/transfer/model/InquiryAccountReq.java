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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Inquiry Account Request.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 22, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class InquiryAccountReq implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    
    /**
     * account type.
     */    
    @NotNull(message = "amount is Required!")
    @JsonProperty("amount")
    private BigDecimal amount;
    
    //@NotBlank(message = "from Account Name is Required!")
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("from")
    private PostingFromReq postingFrom;
    
    @JsonProperty("to")
    private PostingToReq postingTo;
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public InquiryAccountReq() {
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
        builder.append("message", message);
        builder.append("postingFrom", postingFrom);
        builder.append("postingTo", postingTo);
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
		InquiryAccountReq other = (InquiryAccountReq) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
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
		
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());		
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((postingFrom == null) ? 0 : postingFrom.hashCode());
		result = prime * result + ((postingTo == null) ? 0 : postingTo.hashCode());
		return result;
	}
}
