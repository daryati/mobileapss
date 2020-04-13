/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.user.model.AccountInfoStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.AccountStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.CIFStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.payload.AccountBalanceSimpleResp;
import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Account Info Entity Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Entity
@Table(name = "ACCOUNT_INFO")
@JsonIgnoreProperties({ "accountCard", "createDate" })
public class AccountInfo extends IdBasedObject implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1235162180355182822L;

    /* Constants: */

    /* Attributes: */
    /**
     * Account Name
     */
    @Column(name = "ACCOUNT_NAME", length = 50)
    private String accountName;

    /**
     * Account Number
     */
    @Column(name = "ACCOUNT_NO", length = 50)
    private String accountNo;

    /**
     * Account Status. get from WSDL account status. The Status of Account : "0" Not
     * authorized "1" Active "2" Passive "9" Closed
     */
    @Enumerated
    @Column(name = "ACCOUNT_STATUS", columnDefinition = "smallint")
    private AccountStatusEnum accountStatus;

    @Enumerated
    @Column(name = "STATUS", columnDefinition = "smallint")
    private AccountInfoStatusEnum status;

    /**
     * Account Type. Debit, CC, etc.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE")
    private AccountTypeEnum accountType;

    /**
     * CIF Number
     */
    @Column(name = "CIF", length = 20)
    private String cif;
    
    // is cifStatus transient?
    @Column(name = "CIF_STATUS", length = 20)
    private CIFStatusEnum cifStatus;
    

    /**
     * Main Account Flag Y/N
     */
    @Type(type = "yes_no")
    @Column(name = "IS_MAIN_ACCOUNT")
    private boolean isMainAccount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * Join AccountCard Table
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ACCOUNT_CARD_ID")
    private AccountCard accountCard;

    /**
     * Join Product Table
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    /* Transient Attributes: */
    @Transient
    @JsonProperty
    private AccountBalanceSimpleResp accountBalance;
    
    /**
     * Set flag “notActivated“ with true to Accounts Number that couldn’t be set as Main Account.
     */
    @Transient
    private boolean notActivated;

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
     * Gets <code>isMainAccount</code>.
     * 
     * @return The <code>isMainAccount</code>.
     */
    public boolean isMainAccount() {
	return isMainAccount;
    }

    /**
     * Sets <code>isMainAccount</code>.
     * 
     * @param isMainAccount
     *            The <code>isMainAccount</code> to set.
     */
    public void setMainAccount(boolean isMainAccount) {
	this.isMainAccount = isMainAccount;
    }

    /**
     * Gets <code>createDate</code>.
     * 
     * @return The <code>createDate</code>.
     */
    public Date getCreateDate() {
	return createDate;
    }

    /**
     * Sets <code>createDate</code>.
     * 
     * @param createDate
     *            The <code>createDate</code> to set.
     */
    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    /**
     * Gets <code>accountCard</code>.
     * 
     * @return The <code>accountCard</code>.
     */
    public AccountCard getAccountCard() {
	return accountCard;
    }

    /**
     * Sets <code>accountCard</code>.
     * 
     * @param accountCard
     *            The <code>accountCard</code> to set.
     */
    public void setAccountCard(AccountCard accountCard) {
	this.accountCard = accountCard;
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
     * Gets <code>accountBalance</code>.
     * 
     * @return The <code>accountBalance</code>.
     */
    public AccountBalanceSimpleResp getAccountBalance() {
	return accountBalance;
    }

    /**
     * Sets <code>accountBalance</code>.
     * 
     * @param accountBalance
     *            The <code>accountBalance</code> to set.
     */
    public void setAccountBalance(AccountBalanceSimpleResp accountBalance) {
	this.accountBalance = accountBalance;
    }
    
    public CIFStatusEnum getCifStatus() {
	return cifStatus;
    }

    public void setCifStatus(CIFStatusEnum cifStatus) {
	this.cifStatus = cifStatus;
    }
    
    /**
     * Gets <code>notActivated</code>.
     * 
     * @return The <code>notActivated</code>.
     */
    public boolean isNotActivated() {
	return notActivated;
    }

    /**
     * Sets <code>notActivated</code>.
     * 
     * @param notActivated
     *            The <code>notActivated</code> to set.
     */
    public void setNotActivated(boolean notActivated) {
	this.notActivated = notActivated;
    }
	
    /* Functionalities: */

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountInfo [accountName=" + accountName + ", accountNo=" + accountNo + ", accountStatus="
		+ accountStatus + ", status=" + status + ", accountType=" + accountType + ", cif=" + cif
		+ ", isMainAccount=" + isMainAccount + ", createDate=" + createDate 
		+  ", accountBalance=" + accountBalance
		+ ", notActivated=" + notActivated
		+ "]";
    }
}
