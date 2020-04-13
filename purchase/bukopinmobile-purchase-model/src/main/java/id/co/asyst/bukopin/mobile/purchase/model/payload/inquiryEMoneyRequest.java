/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.payload;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 20, 2020
 * @since 1.1.Alpha1
 */
public class inquiryEMoneyRequest {
    /* Constants: */

    /* Attributes: */
	
	/**
	 *  type 
	 */
    @NotBlank
    @JsonProperty("type")
    private String type;
    
    /**
	 *  customerNumber 
	 */
    @NotBlank
    @JsonProperty("custNo")
    private String customerNumber;

    /**
	 *  amount
	 */
    @NotNull(message = "amount is Required!")
    @JsonProperty("amount")
    private BigDecimal amount;

    /* Transient Attributes: */

    /* Constructors: */

    /**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
	public String getType() {
		return type;
	}

	/**
     * Sets <code>type</code>.
     * 
     * @param type The <code>type</code> to set.
     */
	public void setType(String type) {
		this.type = type;
	}

	/**
     * Gets <code>customerNumber</code>.
     * 
     * @return The <code>customerNumber</code>.
     */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
     * Sets <code>customerNumber</code>.
     * 
     * @param customerNumber The <code>customerNumber</code> to set.
     */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
     * Gets <code>amount</code>.
     * 
     * @return The <code>amount</code>.
     */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
     * Sets <code>amount</code>.
     * 
     * @param amount The <code>amount</code> to set.
     */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "inquiryEmoneyRequest [type=" + type + ", customerNumber="
				+ customerNumber + ", amount=" + amount + "]";
	}

    

}
