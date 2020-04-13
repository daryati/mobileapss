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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * PLN Postpaid Payment Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 15, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PLNPostpaidPaymentRequest {
    /* Constants: */

    /* Attributes: */
    /**
     * Username
     */
    @NotBlank
    private String username;
    
    /**
     * PIN Transaction
     */
    @NotBlank
    private String pinTransaction;
    
    /**
     * Subscriber Number
     */
    @NotBlank
    private String subscriberNumber;
    
    /**
     * Amount 
     */
    @NotNull
    private BigDecimal amount;
    
    /**
     * Penalty Fee
     */
    @NotNull
    private BigDecimal penaltyFee;
    
    /**
     * Admin Fee
     */
    @NotNull
    private BigDecimal adminFee;
    
    /**
     * Total Amount
     */
    @NotNull
    private BigDecimal totalAmount;
    
    /**
     * Account Number 
     */
    @NotBlank
    private String accountNo;
    
    /**
     * Element 11 from tibco
     */
    @NotBlank
    private String element11;
    
    /**
     * Element 48 from tibco
     */
    @NotBlank
    private String element48;
    
    /**
     * Element 61 from tibco
     */
    @NotBlank
    private String element61;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * Gets <code>pinTransaction</code>.
     * @return The <code>pinTransaction</code>.
     */
    public String getPinTransaction() {
        return pinTransaction;
    }

    /**
     * Sets <code>pinTransaction</code>.
     * @param pinTransaction The <code>pinTransaction</code> to set.
     */
    public void setPinTransaction(String pinTransaction) {
        this.pinTransaction = pinTransaction;
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
     * Gets <code>penaltyFee</code>.
     * @return The <code>penaltyFee</code>.
     */
    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    /**
     * Sets <code>penaltyFee</code>.
     * @param penaltyFee The <code>penaltyFee</code> to set.
     */
    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
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
     * Gets <code>accountNo</code>.
     * @return The <code>accountNo</code>.
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets <code>accountNo</code>.
     * @param accountNo The <code>accountNo</code> to set.
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PLNPostpaidPaymentRequest [username=" + username + ", pinTransaction=" + pinTransaction
		+ ", subscriberNumber=" + subscriberNumber + ", amount=" + amount + ", penaltyFee=" + penaltyFee
		+ ", adminFee=" + adminFee + ", totalAmount=" + totalAmount + ", accountNo=" + accountNo
		+ ", element11=" + element11 + ", element48=" + element48 + ", element61=" + element61 + ", remainBill="
		+ "]";
    }
    
}
