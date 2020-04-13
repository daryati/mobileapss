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

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 3, 2019
 * @since 2.0
 */
public class AccountBalanceByAccNoReq implements Serializable {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -282503329867556084L;

	/* Constants: */

	/* Attributes: */
	/**
	 * account type.
	 */
	@NotNull
	@JsonProperty("accountType")
	private BigInteger accountType;
	
	@NotNull
	@JsonProperty("accountNumber")
	private String accountNumber;

	/**
	 * list of accountNumber.
	 */
	/*
	 * @JsonProperty("accountNumbers") private List<String> accountNumbers;
	 */

	@NotBlank
	@JsonProperty("username")
	private String username;

	/* Constructors: */
	/**
	 * Constructor.
	 */
	public AccountBalanceByAccNoReq() {
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
	 * @param accountType The <code>accountType</code> to set.
	 */
	public void setAccountType(BigInteger accountType) {
	    this.accountType = accountType;
	}

	/**
	 * Gets <code>accountNo</code>.
	 * @return The <code>accountNo</code>.
	 */
	public String getAccountNumber() {
	    return accountNumber;
	}

	/**
	 * Sets <code>accountNo</code>.
	 * @param accountNo The <code>accountNo</code> to set.
	 */
	public void setAccountNo(String accountNumber) {
	    this.accountNumber = accountNumber;
	}

	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}

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
	    AccountBalanceByAccNoReq other = (AccountBalanceByAccNoReq) obj;
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
	    return true;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
	    result = prime * result + ((username == null) ? 0 : username.hashCode());
	    return result;
	}
    
}
