/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.payload;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 27, 2020
 * @since 1.2.Alpha1
 */
public class SamolnasInquiryResponse {
    /* Constants: */

    /* Attributes: */
    private String payCode;

    private String subscriberName;

    private String nik;

    private String npkb;

    private String machineNo;

    private BigDecimal amount;

    private BigDecimal adminFee;

    private BigDecimal totalAmount;

    private String element11;

    private String element37;

    private String element61;

    private String codeArra;

    private String codeCbs;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>payCode</code>.
     * 
     * @return The <code>payCode</code>.
     */
    public String getPayCode() {
	return payCode;
    }

    /**
     * Sets <code>payCode</code>.
     * 
     * @param payCode The <code>payCode</code> to set.
     */
    public void setPayCode(String payCode) {
	this.payCode = payCode;
    }

    /**
     * Gets <code>subscriberName</code>.
     * 
     * @return The <code>subscriberName</code>.
     */
    public String getSubscriberName() {
	return subscriberName;
    }

    /**
     * Sets <code>subscriberName</code>.
     * 
     * @param subscriberName The <code>subscriberName</code> to set.
     */
    public void setSubscriberName(String subscriberName) {
	this.subscriberName = subscriberName;
    }

    /**
     * Gets <code>nik</code>.
     * 
     * @return The <code>nik</code>.
     */
    public String getNik() {
	return nik;
    }

    /**
     * Sets <code>nik</code>.
     * 
     * @param nik The <code>nik</code> to set.
     */
    public void setNik(String nik) {
	this.nik = nik;
    }

    /**
     * Gets <code>npkb</code>.
     * 
     * @return The <code>npkb</code>.
     */
    public String getNpkb() {
	return npkb;
    }

    /**
     * Sets <code>npkb</code>.
     * 
     * @param npkb The <code>npkb</code> to set.
     */
    public void setNpkb(String npkb) {
	this.npkb = npkb;
    }

    /**
     * Gets <code>machineNo</code>.
     * 
     * @return The <code>machineNo</code>.
     */
    public String getMachineNo() {
	return machineNo;
    }

    /**
     * Sets <code>machineNo</code>.
     * 
     * @param machineNo The <code>machineNo</code> to set.
     */
    public void setMachineNo(String machineNo) {
	this.machineNo = machineNo;
    }

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
     * @param amount The <code>amount</code> to set.
     */
    public void setAmount(BigDecimal amount) {
	this.amount = amount;
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
     * @param adminFee The <code>adminFee</code> to set.
     */
    public void setAdminFee(BigDecimal adminFee) {
	this.adminFee = adminFee;
    }

    /**
     * Gets <code>totalAmount</code>.
     * 
     * @return The <code>totalAmount</code>.
     */
    public BigDecimal getTotalAmount() {
	return totalAmount;
    }

    /**
     * Sets <code>totalAmount</code>.
     * 
     * @param totalAmount The <code>totalAmount</code> to set.
     */
    public void setTotalAmount(BigDecimal totalAmount) {
	this.totalAmount = totalAmount;
    }

    /**
     * Gets <code>element11</code>.
     * 
     * @return The <code>element11</code>.
     */
    public String getElement11() {
	return element11;
    }

    /**
     * Sets <code>element11</code>.
     * 
     * @param element11 The <code>element11</code> to set.
     */
    public void setElement11(String element11) {
	this.element11 = element11;
    }

    /**
     * Gets <code>element37</code>.
     * 
     * @return The <code>element37</code>.
     */
    public String getElement37() {
	return element37;
    }

    /**
     * Sets <code>element37</code>.
     * 
     * @param element37 The <code>element37</code> to set.
     */
    public void setElement37(String element37) {
	this.element37 = element37;
    }

    /**
     * Gets <code>element61</code>.
     * 
     * @return The <code>element61</code>.
     */
    public String getElement61() {
	return element61;
    }

    /**
     * Sets <code>element61</code>.
     * 
     * @param element61 The <code>element61</code> to set.
     */
    public void setElement61(String element61) {
	this.element61 = element61;
    }

    /**
     * Gets <code>codeArra</code>.
     * 
     * @return The <code>codeArra</code>.
     */
    public String getCodeArra() {
	return codeArra;
    }

    /**
     * Sets <code>codeArra</code>.
     * 
     * @param codeArra The <code>codeArra</code> to set.
     */
    public void setCodeArra(String codeArra) {
	this.codeArra = codeArra;
    }

    /**
     * Gets <code>codeCbs</code>.
     * 
     * @return The <code>codeCbs</code>.
     */
    public String getCodeCbs() {
	return codeCbs;
    }

    /**
     * Sets <code>codeCbs</code>.
     * 
     * @param codeCbs The <code>codeCbs</code> to set.
     */
    public void setCodeCbs(String codeCbs) {
	this.codeCbs = codeCbs;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SamolnasInquiryResponse [payCode=" + payCode + ", subscriberName=" + subscriberName + ", nik=" + nik
		+ ", npkb=" + npkb + ", machineNo=" + machineNo + ", amount=" + amount + ", adminFee=" + adminFee
		+ ", totalAmount=" + totalAmount + ", element11=" + element11 + ", element37=" + element37
		+ ", element61=" + element61 + ", codeArra=" + codeArra + ", codeCbs=" + codeCbs + "]";
    }

}
