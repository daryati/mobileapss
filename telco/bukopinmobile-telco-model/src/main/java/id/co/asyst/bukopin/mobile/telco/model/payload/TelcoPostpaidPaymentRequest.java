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

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 17, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TelcoPostpaidPaymentRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String custNo;
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String type;
    
    @NotBlank
    private String accountNo;
    
    @NotBlank
    private String pin;
    
    @NotBlank
    private String codeArra;
    
    @NotBlank
    private String codeCbs;
    
    @NotBlank
    private String element11;
    
    @NotBlank
    private String element37;
    
    @NotBlank
    private String element61;
    
    @NotBlank
    private String productName;

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

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelcoPostpaidPaymentRequest [custNo=" + custNo + ", username=" + username + ", type=" + type
		+ ", accountNo=" + accountNo + ", pin=" + pin + ", codeArra=" + codeArra + ", codeCbs=" + codeCbs
		+ ", element11=" + element11 + ", element37=" + element37 + ", element61=" + element61
		+ ", productName=" + productName + "]";
    }
    
}
