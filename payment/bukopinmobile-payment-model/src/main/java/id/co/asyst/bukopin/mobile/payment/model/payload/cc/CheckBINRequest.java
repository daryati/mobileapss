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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 20, 2020
 * @since 2.0
 */
public class CheckBINRequest {
	/* Constants: */

	/* Attributes: */
	@NotBlank
	private String username;

	@NotBlank
	private String codeCc;

	@NotBlank
	private String name;

	@NotBlank
	@Size(min = 16, max = 16, message = "{error.invalid.input}")
	private String subscriberNumber;

	@NotBlank
	private String amount;

	/* Transient Attributes: */

	/* Constructors: */
	public CheckBINRequest() {

	}

	/* Getters & setters for attributes: */
	/**
	 * Gets <code>codeCc</code>.
	 * 
	 * @return The <code>codeCc</code>.
	 */
	public String getCodeCc() {
		return codeCc;
	}

	/**
	 * Sets <code>codeCc</code>.
	 * 
	 * @param codeCc The <code>codeCc</code> to set.
	 */
	public void setCodeCc(String codeCc) {
		this.codeCc = codeCc;
	}

	/**
	 * Gets <code>name</code>.
	 * 
	 * @return The <code>name</code>.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets <code>name</code>.
	 * 
	 * @param name The <code>name</code> to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets <code>subscriberNumber</code>.
	 * 
	 * @return The <code>subscriberNumber</code>.
	 */
	public String getSubscriberNumber() {
		return subscriberNumber;
	}

	/**
	 * Sets <code>subscriberNumber</code>.
	 * 
	 * @param subscriberNumber The <code>subscriberNumber</code> to set.
	 */
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/* Getters & setters for transient attributes: */

	/* Functionalities: */
	
	/* Overrides: */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CheckBINRequest [username=" + username + ", codeCc=" + codeCc + ", name=" + name + ", subscriberNumber="
				+ subscriberNumber + ", amount=" + amount + "]";
	}

}
