/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.payload;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.user.model.CIFStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionDetailRes;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 2, 2019
 * @since 2.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetailsMapper implements Serializable{
    /* Constants: */
    /**
     * 
     */
    private static final long serialVersionUID = -2360949661002509478L;
    
    /* Attributes: */

    private String accountNumber;

    private BigInteger accountType;

    private String name;

    private CIFStatusEnum accountStatus;

    private String currency;

    private String branch;

    private String location;

    private String productID;

    private BigInteger cifNumber;

    private CIFStatusEnum cifStatus;

    private BigDecimal effectiveBalance;
    
    private BigDecimal holdAmount;
    
    private BigDecimal availableBalance;
    
    private BigDecimal plafon;
    
    private List<InquiryTransactionDetailRes> transactionDetails;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * Gets <code>accountType</code>.
     * @return The <code>accountType</code>.
     */
    public BigInteger getAccountType() {
        return accountType;
    }

    /**
     * Sets <code>accountType</code>.
     * @param accountType The <code>accountType</code> to set.
     */
    public void setAccountType(BigInteger accountType) {
        this.accountType = accountType;
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
     * Gets <code>accountStatus</code>.
     * @return The <code>accountStatus</code>.
     */
    public CIFStatusEnum getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets <code>accountStatus</code>.
     * @param accountStatus The <code>accountStatus</code> to set.
     */
    public void setAccountStatus(CIFStatusEnum accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets <code>currency</code>.
     * @return The <code>currency</code>.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets <code>currency</code>.
     * @param currency The <code>currency</code> to set.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets <code>branch</code>.
     * @return The <code>branch</code>.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets <code>branch</code>.
     * @param branch The <code>branch</code> to set.
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Gets <code>location</code>.
     * @return The <code>location</code>.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets <code>location</code>.
     * @param location The <code>location</code> to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets <code>productID</code>.
     * @return The <code>productID</code>.
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets <code>productID</code>.
     * @param productID The <code>productID</code> to set.
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * Gets <code>cifNumber</code>.
     * @return The <code>cifNumber</code>.
     */
    public BigInteger getCifNumber() {
        return cifNumber;
    }

    /**
     * Sets <code>cifNumber</code>.
     * @param cifNumber The <code>cifNumber</code> to set.
     */
    public void setCifNumber(BigInteger cifNumber) {
        this.cifNumber = cifNumber;
    }

    /**
     * Gets <code>cifStatus</code>.
     * @return The <code>cifStatus</code>.
     */
    public CIFStatusEnum getCifStatus() {
        return cifStatus;
    }

    /**
     * Sets <code>cifStatus</code>.
     * @param cifStatus The <code>cifStatus</code> to set.
     */
    public void setCifStatus(CIFStatusEnum cifStatus) {
        this.cifStatus = cifStatus;
    }

    /**
     * Gets <code>effectiveBalance</code>.
     * @return The <code>effectiveBalance</code>.
     */
    public BigDecimal getEffectiveBalance() {
        return effectiveBalance;
    }

    /**
     * Sets <code>effectiveBalance</code>.
     * @param effectiveBalance The <code>effectiveBalance</code> to set.
     */
    public void setEffectiveBalance(BigDecimal effectiveBalance) {
        this.effectiveBalance = effectiveBalance;
    }

    /**
     * Gets <code>holdAmount</code>.
     * @return The <code>holdAmount</code>.
     */
    public BigDecimal getHoldAmount() {
        return holdAmount;
    }

    /**
     * Sets <code>holdAmount</code>.
     * @param holdAmount The <code>holdAmount</code> to set.
     */
    public void setHoldAmount(BigDecimal holdAmount) {
        this.holdAmount = holdAmount;
    }

    /**
     * Gets <code>availableBalance</code>.
     * @return The <code>availableBalance</code>.
     */
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    /**
     * Sets <code>availableBalance</code>.
     * @param availableBalance The <code>availableBalance</code> to set.
     */
    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    /**
     * Gets <code>plafon</code>.
     * @return The <code>plafon</code>.
     */
    public BigDecimal getPlafon() {
        return plafon;
    }

    /**
     * Sets <code>plafon</code>.
     * @param plafon The <code>plafon</code> to set.
     */
    public void setPlafon(BigDecimal plafon) {
        this.plafon = plafon;
    }

    /**
     * Gets <code>transactionDetails</code>.
     * @return The <code>transactionDetails</code>.
     */
    public List<InquiryTransactionDetailRes> getTransactionDetails() {
        return transactionDetails;
    }

    /**
     * Sets <code>transactionDetails</code>.
     * @param transactionDetails The <code>transactionDetails</code> to set.
     */
    public void setTransactionDetails(List<InquiryTransactionDetailRes> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
