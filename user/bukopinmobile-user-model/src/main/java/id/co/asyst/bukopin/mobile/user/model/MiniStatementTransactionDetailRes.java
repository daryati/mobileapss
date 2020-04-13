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

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Transaction detail of mini statement Response.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 05, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class MiniStatementTransactionDetailRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
 

    /* Attributes: */
    
    /**
     * Transaction Id.
     */    
    @JsonProperty("transactionId")
    private String transactionId;
    
    /**
     * Transaction Amount.
     */
    @JsonProperty("transactionAmount")
    private BigDecimal transactionAmount;
   
    /**
     * Posting.
     */
    @JsonProperty("posting")
    private String posting;
    
    /**
     * Transaction Date Time.
     */
    @JsonProperty("transactionDateTime")
    private Date transactionDateTime;

    /**
     *Description.
     */
    @JsonProperty("description")
    private String description;
    
    
    
   
    

    /* Constructors: */
    /**
     * Constructor.
     */
    public MiniStatementTransactionDetailRes() {
        // do nothing.
    }

    

    /* Getters & setters for attributes: */

	/**
	 * Gets <code>transactionId</code>.
	 * 
	 * @return The <code>transactionId</code>.
	 */	
    public String getTransactionId() {
		return transactionId;
	}

    /**
     * Sets <code>transactionId</code>.
     * 
     * @param transactionId
     *            The <code>transactionId</code> to set.
     */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Gets <code>transactionAmount</code>.
	 * 
	 * @return The <code>transactionAmount</code>.
	 */	
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	/**
     * Sets <code>transactionAmount</code>.
     * 
     * @param transactionAmount
     *            The <code>transactionAmount</code> to set.
     */
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	/**
	 * Gets <code>posting</code>.
	 * 
	 * @return The <code>posting</code>.
	 */	
	public String getPosting() {
		return posting;
	}

	/**
     * Sets <code>posting</code>.
     * 
     * @param posting
     *            The <code>posting</code> to set.
     */
	public void setPosting(String posting) {
		this.posting = posting;
	}

	/**
	 * Gets <code>transactionDateTime</code>.
	 * 
	 * @return The <code>transactionDateTime</code>.
	 */	
	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	/**
     * Sets <code>transactionDateTime</code>.
     * 
     * @param transactionDateTime
     *            The <code>transactionDateTime</code> to set.
     */
	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	/**
	 * Gets <code>description</code>.
	 * 
	 * @return The <code>description</code>.
	 */	
	public String getDescription() {
		return description;
	}

	/**
     * Sets <code>description</code>.
     * 
     * @param description
     *            The <code>description</code> to set.
     */
	public void setDescription(String description) {
		this.description = description;
	}
	



    

	/*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        
        builder.append("transactionId", transactionId);
        builder.append("transactionAmount", transactionAmount);
        builder.append("posting", posting);
        builder.append("transactionDateTime", transactionDateTime);
        builder.append("description", description);
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
		MiniStatementTransactionDetailRes other = (MiniStatementTransactionDetailRes) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		
		if (posting == null) {
			if (other.posting != null)
				return false;
		} else if (!posting.equals(other.posting))
			return false;
		if (transactionAmount == null) {
			if (other.transactionAmount != null)
				return false;
		} else if (!transactionAmount.equals(other.transactionAmount))
			return false;
		if (transactionDateTime == null) {
			if (other.transactionDateTime != null)
				return false;
		} else if (!transactionDateTime.equals(other.transactionDateTime))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((posting == null) ? 0 : posting.hashCode());
		result = prime * result + ((transactionAmount == null) ? 0 : transactionAmount.hashCode());
		result = prime * result + ((transactionDateTime == null) ? 0 : transactionDateTime.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		return result;
	}
}
