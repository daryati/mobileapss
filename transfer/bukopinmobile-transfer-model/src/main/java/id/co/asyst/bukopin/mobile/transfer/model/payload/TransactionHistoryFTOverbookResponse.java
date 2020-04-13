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

import id.co.asyst.bukopin.mobile.transfer.model.PostingRes;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Mar 9, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TransactionHistoryFTOverbookResponse extends PostingRes {
    /* Constants: */

    /* Attributes: */
    private String productName;
    
    private String bankName;
    
    private BigDecimal totalAmount;

    /**
     * Gets <code>productName</code>.
     * @return The <code>productName</code>.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets <code>productName</code>.
     * @param productName The <code>productName</code> to set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets <code>bankName</code>.
     * @return The <code>bankName</code>.
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets <code>bankName</code>.
     * @param bankName The <code>bankName</code> to set.
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TransactionHistoryFTOverbookResponse [productName=" + productName + ", bankName=" + bankName
		+ ", totalAmount=" + totalAmount + "]";
    }
    
}
