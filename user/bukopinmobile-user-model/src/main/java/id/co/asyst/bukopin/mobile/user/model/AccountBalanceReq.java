/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Inquiry Transaction Response.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class AccountBalanceReq implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */

    /* Attributes: */
    /**
     * account type.
     */
    @NotNull(message = "account type is Required!")
    @JsonProperty("accountType")
    private BigInteger accountType;

    /**
     * list of accountNumber.
     */
    /*
     * @JsonProperty("accountNumbers") private List<String> accountNumbers;
     */

    @NotBlank(message = "username type is Required!")
    @JsonProperty("username")
    private String username;

    /**
     * User's PIN
     */
    @NotBlank
    private String pin;

    /* Constructors: */
    /**
     * Constructor.
     */
    public AccountBalanceReq() {
	// do nothing.
    }

    /* Getters & setters for attributes: */

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
     * @param accountType
     *            The <code>accountType</code> to set.
     */
    public void setAccountType(BigInteger accountType) {
	this.accountType = accountType;
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
     * @param accountType
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

    /* Functionalities: x */

    /* Overrides: */

    /*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
	ToStringBuilder builder = new ToStringBuilder(this);
	builder.append("accountType", accountType);
	builder.append("username", username);
	builder.append("pin", pin);
	return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AccountBalanceReq other = (AccountBalanceReq) obj;
	if (accountType == null) {
	    if (other.accountType != null)
		return false;
	} else if (!accountType.equals(other.accountType))
	    return false;
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	if (pin == null) {
	    if (other.pin != null)
		return false;
	} else if (!pin.equals(other.pin))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
	result = prime * result + ((username == null) ? 0 : username.hashCode());
	result = prime * result + ((pin == null) ? 0 : pin.hashCode());
	return result;
    }
}
