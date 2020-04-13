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

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 28, 2020
 * @since 2.0
 */
public class PurchaseLinkAjaRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    @JsonProperty("type")
    private String type;

    @NotBlank
    @JsonProperty("custNo")
    private String custNo;

    @NotBlank
    @JsonProperty("amount")
    private BigDecimal amount;

    @NotBlank
    @JsonProperty("element48")
    private String element48;

    @NotBlank
    @JsonProperty("element11")
    private String element11;

    @NotBlank
    @JsonProperty("username")
    private String username;

    @NotBlank
    @JsonProperty("accountNo")
    private String accountNo;

    @NotBlank
    @JsonProperty("pin")
    private String pin;
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
     * @param type The <code>type</code> to set.
     */
    public void setType(String type) {
	this.type = type;
    }

    /**
     * Gets <code>telpNum</code>.
     * 
     * @return The <code>telpNum</code>.
     */
    public String getCustNo() {
	return custNo;
    }

    /**
     * Sets <code>telpNum</code>.
     * 
     * @param telpNum The <code>telpNum</code> to set.
     */
    public void setCustNo(String custNo) {
	this.custNo = custNo;
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
     * Gets <code>element48</code>.
     * 
     * @return The <code>element48</code>.
     */
    public String getElement48() {
	return element48;
    }

    /**
     * Sets <code>element48</code>.
     * 
     * @param element48 The <code>element48</code> to set.
     */
    public void setElement48(String element48) {
	this.element48 = element48;
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
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
	this.username = username;
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
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PurchaseLinkAjaRequest [type=" + type + ", custNo=" + custNo + ", amount=" + amount + ", element48="
		+ element48 + ", element11=" + element11 + ", username=" + username + ", accountNo=" + accountNo
		+ ", pin=" + pin + "]";
    }


}
