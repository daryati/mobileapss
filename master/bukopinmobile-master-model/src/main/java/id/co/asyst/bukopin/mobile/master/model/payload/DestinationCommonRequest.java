/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.payload;

import java.math.BigDecimal;

/**
 * Save Destination Common Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 23, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class DestinationCommonRequest {
    /* Constants: */

   



	/* Attributes: */
    /**
     * Category Id from table Category
     */
    private long categoryId;
    
    /**
     * Username
     */
    private String username;
    
    /**
     * Subscriber Number
     */
    private String subscriberNumber;
    
    /**
     * Subscriber Name
     */
    private String subscriberName;
    
    /**
     * Destination Type
     */
    private String destinationType;
    
    /**
     * Transaction Type 
     */
    private String transactionType; 
    
    /**
     * Reference from Tibco response
     */
    private String reference;
    
    /**
     * total Amount for transaction response
     */
    private BigDecimal totalAmount;
    
    /**
     * account Number for transaction response
     */
    private String accountNumber;
    
    private String rrn;
    
    private String refBayar;
    
    private String prodCode;
    
    /**
   	 * @return the rrn
   	 */
   	public String getRrn() {
   		return rrn;
   	}

   	/**
   	 * @param rrn the rrn to set
   	 */
   	public void setRrn(String rrn) {
   		this.rrn = rrn;
   	}

   	/**
   	 * @return the refBayar
   	 */
   	public String getRefBayar() {
   		return refBayar;
   	}

   	/**
   	 * @param refBayar the refBayar to set
   	 */
   	public void setRefBayar(String refBayar) {
   		this.refBayar = refBayar;
   	}

   	/**
   	 * @return the prodCode
   	 */
   	public String getProdCode() {
   		return prodCode;
   	}

   	/**
   	 * @param prodCode the prodCode to set
   	 */
   	public void setProdCode(String prodCode) {
   		this.prodCode = prodCode;
   	}

    
    
    

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>categoryId</code>.
     * @return The <code>categoryId</code>.
     */
    public long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets <code>categoryId</code>.
     * @param categoryId The <code>categoryId</code> to set.
     */
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets <code>username</code>.
     * @return The <code>username</code>.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets <code>username</code>.
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets <code>subscriberNumber</code>.
     * @return The <code>subscriberNumber</code>.
     */
    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    /**
     * Sets <code>subscriberNumber</code>.
     * @param subscriberNumber The <code>subscriberNumber</code> to set.
     */
    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    /**
     * Gets <code>subscriberName</code>.
     * @return The <code>subscriberName</code>.
     */
    public String getSubscriberName() {
        return subscriberName;
    }

    /**
     * Sets <code>subscriberName</code>.
     * @param subscriberName The <code>subscriberName</code> to set.
     */
    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    /**
     * Gets <code>destinationType</code>.
     * @return The <code>destinationType</code>.
     */
    public String getDestinationType() {
        return destinationType;
    }

    /**
     * Sets <code>destinationType</code>.
     * @param destinationType The <code>destinationType</code> to set.
     */
    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    /**
     * Gets <code>reference</code>.
     * @return The <code>reference</code>.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets <code>reference</code>.
     * @param reference The <code>reference</code> to set.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    /**
     * Gets <code>transactionType</code>.
     * @return The <code>transactionType</code>.
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets <code>transactionType</code>.
     * @param transactionType The <code>transactionType</code> to set.
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DestinationCommonRequest [categoryId=" + categoryId
				+ ", username=" + username + ", subscriberNumber="
				+ subscriberNumber + ", subscriberName=" + subscriberName
				+ ", destinationType=" + destinationType + ", transactionType="
				+ transactionType + ", reference=" + reference
				+ ", totalAmount=" + totalAmount + ", accountNumber="
				+ accountNumber + ", rrn=" + rrn + ", refBayar=" + refBayar
				+ ", prodCode=" + prodCode + "]";
	}
	
	
	

    /* Functionalities: */

    

	/* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
	
	
	
//    @Override
//    public String toString() {
//	return "DestinationCommonRequest [categoryId=" + categoryId + ", username=" + username + ", subscriberNumber="
//		+ subscriberNumber + ", subscriberName=" + subscriberName + ", destinationType=" + destinationType
//		+ ", transactionType=" + transactionType
//		+ ", reference=" + reference + "]";
//    }
}
