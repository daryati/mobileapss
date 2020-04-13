/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.payload;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 27, 2020
 * @since 2.0
 */
public class InquiryLinkAjaResponse {
    /* Constants: */

    /* Attributes: */
    private String custNo;
    
    private String custName;
    
    private BigDecimal amountFee;

    private BigDecimal amount;
    
    private BigDecimal totalAmount;
    
    private String element11;
    
    private String element48;
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>telpNumb</code>.
     * @return The <code>telpNumb</code>.
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * Sets <code>telpNumb</code>.
     * @param telpNumb The <code>telpNumb</code> to set.
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * Gets <code>custName</code>.
     * @return The <code>custName</code>.
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets <code>custName</code>.
     * @param custName The <code>custName</code> to set.
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * Gets <code>amountFee</code>.
     * @return The <code>amountFee</code>.
     */
    public BigDecimal getAmountFee() {
        return amountFee;
    }

    /**
     * Sets <code>amountFee</code>.
     * @param amountFee The <code>amountFee</code> to set.
     */
    public void setAmountFee(BigDecimal amountFee) {
        this.amountFee = amountFee;
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
     * Gets <code>element48</code>.
     * @return The <code>element48</code>.
     */
    public String getElement48() {
        return element48;
    }

    /**
     * Sets <code>element48</code>.
     * @param element48 The <code>element48</code> to set.
     */
    public void setElement48(String element48) {
        this.element48 = element48;
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
     * Gets <code>element11</code>.
     * @return The <code>element11</code>.
     */
    public String getElement11() {
        return element11;
    }

    /**
     * Sets <code>element11</code>.
     * @param element11 The <code>element11</code> to set.
     */
    public void setElement11(String element11) {
        this.element11 = element11;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "InquiryLinkAjaResponse [custNo=" + custNo + ", custName=" + custName + ", amountFee=" + amountFee
		+ ", amount=" + amount + ", totalAmount=" + totalAmount + ", element11=" + element11 + ", element48="
		+ element48 + "]";
    }
}
