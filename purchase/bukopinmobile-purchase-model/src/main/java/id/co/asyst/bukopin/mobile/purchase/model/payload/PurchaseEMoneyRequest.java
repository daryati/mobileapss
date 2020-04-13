/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.payload;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 21, 2020
 * @since 1.1.Alpha1
 */
public class PurchaseEMoneyRequest {
    /* Constants: */

    /* Attributes: */
	
	/**
     * type
     */
    @NotBlank
    @JsonProperty("type")
    private String type;
    
	/**
     * customerNumber
     */
    @NotBlank
    @JsonProperty("custNo")
    private String customerNumber;

	/**
     * amount
     */
    @NotNull(message = "amount is Required!")
    @JsonProperty("amount")
    private BigDecimal amount;
    
	/**
     * accountNumber
     */
    @NotBlank
    @JsonProperty("accountNo")
    private String accountNumber;
    
	/**
     * element61
     */
    @NotBlank
    @JsonProperty("element61")
    private String element61;
    
	/**
     * element11
     */
    @NotBlank
    @JsonProperty("element11")
    private String element11;
    
	/**
     * username
     */
    @NotBlank
    @JsonProperty("username")
    private String username;
    
	/**
     * pin
     */
    @NotBlank
    @JsonProperty("pin")
    private String pin;
    
    /**
     * Element 37
     */
    @JsonProperty("element37")
    private String element37;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
	public String getType() {
		return type;
	}

	/**
     * Sets <code>type</code>.
     * 
     * @param type
     *            The <code>type</code> to set.
     */
	public void setType(String type) {
		this.type = type;
	}

	/**
     * Gets <code>customerNumber</code>.
     * 
     * @return The <code>customerNumber</code>.
     */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
     * Sets <code>customerNumber</code>.
     * 
     * @param customerNumber
     *            The <code>customerNumber</code> to set.
     */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
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
     * @param amount
     *            The <code>amount</code> to set.
     */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
     * @param accountNumber
     *            The <code>accountNumber</code> to set.
     */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
     * @param element61
     *            The <code>element61</code> to set.
     */
	public void setElement61(String element61) {
		this.element61 = element61;
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
     * @param element11
     *            The <code>element11</code> to set.
     */
	public void setElement11(String element11) {
		this.element11 = element11;
	}
	
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
     * @param username
     *            The <code>username</code> to set.
     */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets <code>pin</code>.
	 * @return The <code>pin</code>.
	 */
	public String getPin() {
	    return pin;
	}

	/**
	 * Sets <code>pin</code>.
	 * @param pin The <code>pin</code> to set.
	 */
	public void setPin(String pin) {
	    this.pin = pin;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "PurchaseEMoneyRequest [type=" + type + ", customerNumber=" + customerNumber + ", amount=" + amount
		    + ", accountNumber=" + accountNumber + ", element61=" + element61 + ", element11=" + element11
		    + ", element37=" + element37
		    + ", username=" + username + ", pin=" + pin + "]";
	}

    
}
