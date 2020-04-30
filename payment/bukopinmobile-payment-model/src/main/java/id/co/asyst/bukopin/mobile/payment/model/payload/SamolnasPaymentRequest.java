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
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 28, 2020
 * @since 1.2.Alpha1
 */
public class SamolnasPaymentRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String username;

    @NotBlank
    private String pin;

    @NotBlank
    private String payCode;

    @NotBlank
    private String nik;

    @NotBlank
    private String accountNumber;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BigDecimal adminFee;

    @NotBlank
    private String element11;

    @NotBlank
    private String element37;

    @NotBlank
    private String element61;

    @NotBlank
    private String codeArra;

    @NotBlank
    private String codeCbs;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
    public String getUsername() {
	return username;
    }

    /**
     * Sets <code>username</code>.
     * 
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * Gets <code>pin</code>.
     * 
     * @return The <code>pin</code>.
     */
    public String getPin() {
	return pin;
    }

    /**
     * Sets <code>pin</code>.
     * 
     * @param pin The <code>pin</code> to set.
     */
    public void setPin(String pin) {
	this.pin = pin;
    }

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
     * Gets <code>accountNumber</code>.
     * 
     * @return The <code>accountNumber</code>.
     */
    public String getAccountNumber() {
	return accountNumber;
    }

    /**
     * Sets <code>accountNumber</code>.
     * 
     * @param accountNumber The <code>accountNumber</code> to set.
     */
    public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
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
	return "SamolnasPaymentRequest [username=" + username + ", pin=" + pin + ", payCode=" + payCode + ", nik=" + nik
		+ ", accountNumber=" + accountNumber + ", amount=" + amount + ", adminFee=" + adminFee + ", element11="
		+ element11 + ", element37=" + element37 + ", element61=" + element61 + ", codeArra=" + codeArra
		+ ", codeCbs=" + codeCbs + "]";
    }

}
