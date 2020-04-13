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
 * @author Gibran Haq
 * @version $Revision$, Mar 5, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TransactionHistoryResponse {
    /* Constants: */

    /* Attributes: */
    private Long idDetail;
    
    private String dateTime;
    
    private String amount;
    
    private String referenceNumber;
    
    private String accountNumber;
    
    private String type;
    
    private String note;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>idDetail</code>.
     * @return The <code>idDetail</code>.
     */
    public Long getIdDetail() {
        return idDetail;
    }

    /**
     * Sets <code>idDetail</code>.
     * @param idDetail The <code>idDetail</code> to set.
     */
    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

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
     * Gets <code>amount</code>.
     * @return The <code>amount</code>.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets <code>amount</code>.
     * @param amount The <code>amount</code> to set.
     */
    public void setAmount(String amount) {
        this.amount = amount;
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
     * Gets <code>type</code>.
     * @return The <code>type</code>.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets <code>type</code>.
     * @param type The <code>type</code> to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Gets <code>note</code>.
     * @return The <code>note</code>.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets <code>note</code>.
     * @param note The <code>note</code> to set.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TransactionHistoryResponse [idDetail=" + idDetail + ", dateTime=" + dateTime + ", amount=" + amount
		+ ", referenceNumber=" + referenceNumber + ", accountNumber=" + accountNumber + ", type=" + type
		+ ", note=" + note + "]";
    }
}
