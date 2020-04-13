/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Mar 10, 2020
 * @since 2.0
 */
public class AccountStatementReq {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */

    /* Attributes: */

    /**
     * date from
     */
    @JsonProperty("dateFrom")
    private Date dateFrom;

    /**
     * date to
     */
    @JsonProperty("dateTo")
    private Date dateTo;

    /**
     * account number
     */
    @NotBlank(message = "account number is Required!")
    @JsonProperty("accountNumber")
    private String accountNumber;

    /**
     * Account Type.
     */
    @NotNull(message = "account type is Required!")
    @JsonProperty("accountType")
    private BigInteger accountType;

    /**
     * Tx Line ()
     */
    @JsonProperty("txLine")
    private BigInteger txLine;

    /**
     * username
     */
    @JsonProperty("username")
    private String username;
    /* Constructors: */
    /**
     * Constructor.
     */
    public AccountStatementReq() {
	// do nothing.
    }

    /**
     * Constructor with clientTxId parameter.
     * 
     * @param clientTxId The clientTxId.
     */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>dateFrom</code>.
     * 
     * @return The <code>dateFrom</code>.
     */
    public Date getDateFrom() {
	return dateFrom;
    }

    /**
     * Sets <code>dateFrom</code>.
     * 
     * @param dateFrom The <code>dateFrom</code> to set.
     */
    public void setDateFrom(Date dateFrom) {
	this.dateFrom = dateFrom;
    }

    /**
     * Gets <code>dateTo</code>.
     * 
     * @return The <code>dateTo</code>.
     */
    public Date getDateTo() {
	return dateTo;
    }

    /**
     * Sets <code>dateTo</code>.
     * 
     * @param dateTo The <code>dateTo</code> to set.
     */
    public void setDateTo(Date dateTo) {
	this.dateTo = dateTo;
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
     * Gets <code>accountType</code>.
     * 
     * @return The <code>accountType</code>.
     */
    public BigInteger getAccountType() {
	return accountType;
    }

    /**
     * Sets <code>accountType</code>.
     * 
     * @param accountType The <code>accountType</code> to set.
     */
    public void setAccountType(BigInteger accountType) {
	this.accountType = accountType;
    }

    /**
     * Gets <code>txLine</code>.
     * 
     * @return The <code>txLine</code>.
     */
    public BigInteger getTxLine() {
	return txLine;
    }

    /**
     * Sets <code>txLine</code>.
     * 
     * @param txLine The <code>txLine</code> to set.
     */
    public void setTxLine(BigInteger txLine) {
	this.txLine = txLine;
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

    
    /* Functionalities: x */

    /* Overrides: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountStatementReq [dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", accountNumber=" + accountNumber
		+ ", accountType=" + accountType + ", txLine=" + txLine + ", username=" + username + "]";
    }
    
}
