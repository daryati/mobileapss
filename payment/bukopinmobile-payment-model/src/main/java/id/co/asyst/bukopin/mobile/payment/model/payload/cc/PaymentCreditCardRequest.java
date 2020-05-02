/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.payload.cc;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 2, 2020
 * @since 2.0
 */
public class PaymentCreditCardRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String username;
    
    @NotBlank
    private String pin;
    
    @NotBlank
    private String codeCc;
    
    @NotBlank
    private String name;
    
    @NotBlank
    @Size(message = "{error.invalid.input}", min = 16)
    private String subscriberNumber;
    
    private String subscriberName;
    
    private String billedAmount;
    
    private String minimumPayment;
   
    @NotBlank
    private String amount;
    
    @NotBlank
    private String accountNumber;
    
    private String element11;
    
    private String element37;
    
    private String element120;

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
     * Gets <code>codeCc</code>.
     * @return The <code>codeCc</code>.
     */
    public String getCodeCc() {
        return codeCc;
    }
    /**
     * Sets <code>codeCc</code>.
     * @param codeCc The <code>codeCc</code> to set.
     */
    public void setCodeCc(String codeCc) {
        this.codeCc = codeCc;
    }
    /**
     * Gets <code>name</code>.
     * @return The <code>name</code>.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets <code>name</code>.
     * @param name The <code>name</code> to set.
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets <code>subscriberName</code>.
     * @return The <code>subscriberName</code>.
     */
    public String getSubscriberName() {
        return subscriberName;
    }
    /**
     * Sets <code>subscriberName</code>.
     * @param subscriberName The <code>subscriberName</code> to set.
     */
    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }
    /**
     * Gets <code>billedAmount</code>.
     * @return The <code>billedAmount</code>.
     */
    public String getBilledAmount() {
        return billedAmount;
    }
    /**
     * Sets <code>billedAmount</code>.
     * @param billedAmount The <code>billedAmount</code> to set.
     */
    public void setBilledAmount(String billedAmount) {
        this.billedAmount = billedAmount;
    }
    /**
     * Gets <code>minimumPayment</code>.
     * @return The <code>minimumPayment</code>.
     */
    public String getMinimumPayment() {
        return minimumPayment;
    }
    /**
     * Sets <code>minimumPayment</code>.
     * @param minimumPayment The <code>minimumPayment</code> to set.
     */
    public void setMinimumPayment(String minimumPayment) {
        this.minimumPayment = minimumPayment;
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
     * Gets <code>element120</code>.
     * @return The <code>element120</code>.
     */
    public String getElement120() {
        return element120;
    }
    /**
     * Sets <code>element120</code>.
     * @param element120 The <code>element120</code> to set.
     */
    public void setElement120(String element120) {
        this.element120 = element120;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PaymentCreditCardRequest [username=" + username + ", pin=" + pin + ", codeCc=" + codeCc + ", name="
		+ name + ", subscriberNumber=" + subscriberNumber + ", subscriberName=" + subscriberName
		+ ", billedAmount=" + billedAmount + ", minimumPayment=" + minimumPayment + ", amount=" + amount
		+ ", accountNumber=" + accountNumber + ", element11=" + element11 + ", element37=" + element37
		+ ", element120=" + element120 + "]";
    }

}
