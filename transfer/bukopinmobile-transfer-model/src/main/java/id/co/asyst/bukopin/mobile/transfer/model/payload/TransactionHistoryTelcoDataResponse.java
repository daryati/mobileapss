/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.payload;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Sep 02, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */

public class TransactionHistoryTelcoDataResponse {

    private String dateTime;
    
    private String referenceNumber;
    
    private String accountNumber;
    
    private String phoneNumber;
   
    private String title;
    
    private String typeData;
    
    private BigDecimal amount;
    
    private BigDecimal adminFee;
    
    private BigDecimal totalAmount;
    
    private String alias;
    
 private String rrn;
    
    private String refBayar;
    
    private String prodCode;

    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */


    /**
     * Gets <code>dateTime</code>.
     * @return The <code>dateTime</code>.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets <code>dateTime</code>.
     * @param dateTime The <code>dateTime</code> to set.
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets <code>referenceNumber</code>.
     * @return The <code>referenceNumber</code>.
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets <code>referenceNumber</code>.
     * @param referenceNumber The <code>referenceNumber</code> to set.
     */
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    /**
     * Gets <code>accountNumber</code>.
     * @return The <code>accountNumber</code>.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets <code>accountNumber</code>.
     * @param accountNumber The <code>accountNumber</code> to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets <code>phoneNumber</code>.
     * @return The <code>phoneNumber</code>.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets <code>phoneNumber</code>.
     * @param phoneNumber The <code>phoneNumber</code> to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets <code>pGroup</code>.
     * @return The <code>pGroup</code>.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets <code>pGroup</code>.
     * @param pGroup The <code>pGroup</code> to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets <code>typeTelco</code>.
     * @return The <code>typeTelco</code>.
     */
    public String getTypeData() {
        return typeData;
    }

    /**
     * Sets <code>typeTelco</code>.
     * @param typeTelco The <code>typeTelco</code> to set.
     */
    public void setTypeData(String typeData) {
        this.typeData = typeData;
    }

    /**
     * Gets <code>adminFee</code>.
     * @return The <code>adminFee</code>.
     */
    public BigDecimal getAdminFee() {
        return adminFee;
    }

    /**
     * Sets <code>adminFee</code>.
     * @param adminFee The <code>adminFee</code> to set.
     */
    public void setAdminFee(BigDecimal adminFee) {
        this.adminFee = adminFee;
    }

    /**
     * Gets <code>amount</code>.
     * @return The <code>amount</code>.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets <code>amount</code>.
     * @param amount The <code>amount</code> to set.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets <code>totalAmount</code>.
     * @return The <code>totalAmount</code>.
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets <code>totalAmount</code>.
     * @param totalAmount The <code>totalAmount</code> to set.
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    /**
     * Gets <code>alias</code>.
     * @return The <code>alias</code>.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets <code>alias</code>.
     * @param alias The <code>alias</code> to set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    
    

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionHistoryTelcoDataResponse [dateTime=" + dateTime
				+ ", referenceNumber=" + referenceNumber + ", accountNumber="
				+ accountNumber + ", phoneNumber=" + phoneNumber + ", title="
				+ title + ", typeData=" + typeData + ", amount=" + amount
				+ ", adminFee=" + adminFee + ", totalAmount=" + totalAmount
				+ ", alias=" + alias + ", rrn=" + rrn + ", refBayar="
				+ refBayar + ", prodCode=" + prodCode + "]";
	}

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
//    @Override
//    public String toString() {
//	return "TransactionHistoryTelcoPrepaid [dateTime=" + dateTime + ", referenceNumber=" + referenceNumber
//		+ ", accountNumber=" + accountNumber + ", phoneNumber=" + phoneNumber + ", title=" + title + ", typeData=" + typeData + ", adminFee=" + adminFee
//		+ ", amount=" + amount + ", totalAmount=" + totalAmount + ", alias=" + alias + "]";
//    }
	
	
}
