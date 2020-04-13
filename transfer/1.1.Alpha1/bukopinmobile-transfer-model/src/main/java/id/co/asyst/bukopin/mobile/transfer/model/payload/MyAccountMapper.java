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

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import id.co.asyst.bukopin.mobile.user.model.AccountInfoStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.AccountStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.entity.Product;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 2, 2019
 * @since 2.0
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class MyAccountMapper {
    /* Constants: */

    private Long id;
    
    private String accountName;

    private String accountNo;

    private AccountStatusEnum accountStatus;

    private AccountInfoStatusEnum status;

    private AccountTypeEnum accountType;

    private String cif;

    private BigInteger pdId;

    private Product product;
    
    private boolean mainAccount;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>accountName</code>.
     * 
     * @return The <code>accountName</code>.
     */
    public String getAccountName() {
	return accountName;
    }

    /**
     * Sets <code>accountName</code>.
     * 
     * @param accountName
     *            The <code>accountName</code> to set.
     */
    public void setAccountName(String accountName) {
	this.accountName = accountName;
    }

    /**
     * Gets <code>accountNo</code>.
     * 
     * @return The <code>accountNo</code>.
     */
    public String getAccountNo() {
	return accountNo;
    }

    /**
     * Sets <code>accountNo</code>.
     * 
     * @param accountNo
     *            The <code>accountNo</code> to set.
     */
    public void setAccountNo(String accountNo) {
	this.accountNo = accountNo;
    }

    /**
     * Gets <code>accountStatus</code>.
     * 
     * @return The <code>accountStatus</code>.
     */
    public AccountStatusEnum getAccountStatus() {
	return accountStatus;
    }

    /**
     * Sets <code>accountStatus</code>.
     * 
     * @param accountStatus
     *            The <code>accountStatus</code> to set.
     */
    public void setAccountStatus(AccountStatusEnum accountStatus) {
	this.accountStatus = accountStatus;
    }

    /**
     * Gets <code>accountType</code>.
     * 
     * @return The <code>accountType</code>.
     */
    public AccountTypeEnum getAccountType() {
	return accountType;
    }

    /**
     * Sets <code>accountType</code>.
     * 
     * @param accountType
     *            The <code>accountType</code> to set.
     */
    public void setAccountType(AccountTypeEnum accountType) {
	this.accountType = accountType;
    }

    /**
     * Gets <code>cif</code>.
     * 
     * @return The <code>cif</code>.
     */
    public String getCif() {
	return cif;
    }

    /**
     * Sets <code>cif</code>.
     * 
     * @param cif
     *            The <code>cif</code> to set.
     */
    public void setCif(String cif) {
	this.cif = cif;
    }

    /**
     * Gets <code>pdId</code>.
     * 
     * @return The <code>pdId</code>.
     */
    public BigInteger getPdId() {
	return pdId;
    }

    /**
     * Sets <code>pdId</code>.
     * 
     * @param pdId
     *            The <code>pdId</code> to set.
     */
    public void setPdId(BigInteger pdId) {
	this.pdId = pdId;
    }

    /**
     * Gets <code>status</code>.
     * 
     * @return The <code>status</code>.
     */
    public AccountInfoStatusEnum getStatus() {
	return status;
    }

    /**
     * Sets <code>status</code>.
     * 
     * @param status
     *            The <code>status</code> to set.
     */
    public void setStatus(AccountInfoStatusEnum status) {
	this.status = status;
    }

    /* Getters & setters for transient attributes: */
    /**
     * Gets <code>product</code>.
     * 
     * @return The <code>product</code>.
     */
    public Product getProduct() {
	return product;
    }

    /**
     * Sets <code>product</code>.
     * 
     * @param product
     *            The <code>product</code> to set.
     */
    public void setProduct(Product product) {
	this.product = product;
    }

    /**
     * Gets <code>mainAccount</code>.
     * @return The <code>mainAccount</code>.
     */
    public boolean getMainAccount() {
        return mainAccount;
    }

    /**
     * Sets <code>mainAccount</code>.
     * @param mainAccount The <code>mainAccount</code> to set.
     */
    public void setMainAccount(boolean mainAccount) {
        this.mainAccount = mainAccount;
    }

   

    /* Overrides: */}
