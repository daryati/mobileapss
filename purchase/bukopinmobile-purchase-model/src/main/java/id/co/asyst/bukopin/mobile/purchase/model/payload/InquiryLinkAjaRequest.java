/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.payload;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 24, 2020
 * @since 2.0
 */
public class InquiryLinkAjaRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    @JsonProperty("type")
    private String type;

    @NotBlank
    @JsonProperty("custNo")
    private String custNo;

    @NotBlank
    @JsonProperty("amount")
    private BigDecimal amount;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * Gets <code>telpNumb</code>.
     * 
     * @return The <code>telpNumb</code>.
     */
    public String getCustNo() {
	return custNo;
    }

    /**
     * Sets <code>telpNumb</code>.
     * 
     * @param telpNumb The <code>telpNumb</code> to set.
     */
    public void setCustNo(String custNo) {
	this.custNo = custNo;
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

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "InquiryLinkAjaRequest [type=" + type + ", custNo=" + custNo + ", amount=" + amount + "]";
    }
}
