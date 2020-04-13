/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Receiver Info Entity Model
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 14, 2019
 * @since 2.0
 */
@Entity
@Table(name = "RECEIVER_INFO")
@JsonInclude(Include.NON_NULL)
public class ReceiverInfo extends IdBasedObject implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    /*
     * @Id
     * 
     * @GeneratedValue
     * 
     * @NotBlank
     * 
     * @Column(name = "ID_RECEIVER_INFO") private Long id;
     */

    /**
     * account Name
     */
    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    /**
     * account Number
     */
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    /**
     *alias
     */
    @Column(name = "ALIAS")
    private String alias;

    /**
     * counter
     */
    @Column(name = "COUNTER")
    private Integer counter;

    /**
     * bank id
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    /*
     * fund transfer
     */
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "receiverInfo", cascade = CascadeType.ALL)
    private List<FundTransfer> fundTransfer;

    /**
     * username
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private UserTransfer username;

    /**
     * is save
     */
    @Column(name = "IS_SAVE")
    private boolean isSave;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /**
     * Gets <code>id</code>.
     * 
     * @return The <code>id</code>.
     */
    public Long getId() {
	return id;
    }

    /**
     * Sets <code>id</code>.
     * 
     * @param id
     *            The <code>id</code> to set.
     */
    public void setId(Long id) {
	this.id = id;
    }

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
     * Gets <code>alias</code>.
     * 
     * @return The <code>alias</code>.
     */
    public String getAlias() {
	return alias;
    }

    /**
     * Sets <code>alias</code>.
     * 
     * @param alias
     *            The <code>alias</code> to set.
     */
    public void setAlias(String alias) {
	this.alias = alias;
    }

    /**
     * Gets <code>counter</code>.
     * 
     * @return The <code>counter</code>.
     */
    public Integer getCounter() {
	return counter;
    }

    /**
     * Sets <code>counter</code>.
     * 
     * @param counter
     *            The <code>counter</code> to set.
     */
    public void setCounter(Integer counter) {
	this.counter = counter;
    }

    /**
     * Gets <code>fundTransfer</code>.
     * 
     * @return The <code>fundTransfer</code>.
     */
    public List<FundTransfer> getFundTransfer() {
	return fundTransfer;
    }

    /**
     * Sets <code>fundTransfer</code>.
     * 
     * @param counter
     *            The <code>fundTransfer</code> to set.
     */
    public void setFundTransfer(List<FundTransfer> fundTransfer) {
	this.fundTransfer = fundTransfer;
    }

    /**
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
    public UserTransfer getUsername() {
	return username;
    }

    /**
     * Sets <code>username</code>.
     * 
     * @param counter
     *            The <code>username</code> to set.
     */
    public void setUsername(UserTransfer username) {
	this.username = username;
    }

    /**
     * Gets <code>bank</code>.
     * 
     * @return The <code>bank</code>.
     */
    public Bank getBank() {
	return bank;
    }

    /**
     * Sets <code>bank</code>.
     * 
     * @param counter
     *            The <code>bank</code> to set.
     */
    public void setBank(Bank bank) {
	this.bank = bank;
    }

    /**
     * Gets <code>isSave</code>.
     * 
     * @return The <code>isSave</code>.
     */
    public boolean isSave() {
	return isSave;
    }

    /**
     * Sets <code>isSave</code>.
     * 
     * @param isSave
     *            The <code>isSave</code> to set.
     */
    public void setSave(boolean isSave) {
	this.isSave = isSave;
    }

    @Override
    public String toString() {
	return "ReceiverInfo [id=" + id + ", accountName=" + accountName + ", accountNumber=" + accountNumber
		+ ", alias=" + alias + ", counter=" + counter + ", bank=" + bank + "";
    }

}
