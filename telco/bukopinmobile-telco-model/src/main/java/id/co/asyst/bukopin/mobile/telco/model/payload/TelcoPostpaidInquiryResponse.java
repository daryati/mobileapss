/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model.payload;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 13, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TelcoPostpaidInquiryResponse {
    /* Constants: */

    /* Attributes: */
    private String custNo;
    
    private String custName;
    
    private String type;
    
    private String codeArra;
    
    private String codeCbs;
    
    private String billPeriode;
    
    private String productName;
    
    private BigDecimal amountFee;
    
    private BigDecimal amount;
    
    private BigDecimal totalAmount;
    
    private String element11;
    
    private String element37;
    
    private String element61;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>custNo</code>.
     * @return The <code>custNo</code>.
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * Sets <code>custNo</code>.
     * @param custNo The <code>custNo</code> to set.
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
     * Gets <code>billPeriode</code>.
     * @return The <code>billPeriode</code>.
     */
    public String getBillPeriode() {
        return billPeriode;
    }

    /**
     * Sets <code>billPeriode</code>.
     * @param billPeriode The <code>billPeriode</code> to set.
     */
    public void setBillPeriode(String billPeriode) {
        this.billPeriode = billPeriode;
    }

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

    /**
     * Gets <code>element37</code>.
     * @return The <code>element37</code>.
     */
    public String getElement37() {
        return element37;
    }

    /**
     * Sets <code>element37</code>.
     * @param element37 The <code>element37</code> to set.
     */
    public void setElement37(String element37) {
        this.element37 = element37;
    }

    /**
     * Gets <code>element61</code>.
     * @return The <code>element61</code>.
     */
    public String getElement61() {
        return element61;
    }

    /**
     * Sets <code>element61</code>.
     * @param element61 The <code>element61</code> to set.
     */
    public void setElement61(String element61) {
        this.element61 = element61;
    }
    /**
     * Gets <code>codeArra</code>.
     * @return The <code>codeArra</code>.
     */
    public String getCodeArra() {
        return codeArra;
    }

    /**
     * Sets <code>codeArra</code>.
     * @param codeArra The <code>codeArra</code> to set.
     */
    public void setCodeArra(String codeArra) {
        this.codeArra = codeArra;
    }

    /**
     * Gets <code>codeCbs</code>.
     * @return The <code>codeCbs</code>.
     */
    public String getCodeCbs() {
        return codeCbs;
    }

    /**
     * Sets <code>codeCbs</code>.
     * @param codeCbs The <code>codeCbs</code> to set.
     */
    public void setCodeCbs(String codeCbs) {
        this.codeCbs = codeCbs;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelcoPostpaidInquiryResponse [custNo=" + custNo + ", custName=" + custName + ", type=" + type
		+ ", codeArra=" + codeArra + ", codeCbs=" + codeCbs + ", billPeriode=" + billPeriode + ", productName="
		+ productName + ", amountFee=" + amountFee + ", amount=" + amount + ", totalAmount=" + totalAmount
		+ ", element11=" + element11 + ", element37=" + element37 + ", element61=" + element61 + "]";
    }
}
