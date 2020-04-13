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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 20, 2020
 * @since 1.1.Alpha1
 */
@JsonInclude(Include.NON_NULL)
public class inquiryEMoneyResponse {
    /* Constants: */

    /* Attributes: */
	
	/**
     *  customerName 
	 */
    @JsonProperty("custName")
    private String customerName;
    
    /**
     *  customerNumber 
	 */
    @JsonProperty("custNo")
    private String customerNumber;

    /**
     *  amount 
	 */
    @JsonProperty("amount")
    private BigDecimal amount;
    
    /**
     *  amountFee 
	 */
    @JsonProperty("amountFee")
    private BigDecimal amountFee;
    
    /**
     *  element61 
	 */
    @JsonProperty("element61")
    private String element61;
    
    /**
     *  element11 
	 */
    @JsonProperty("element11")
    private String element11;
    
    /**
     *  element48 
	 */
    @JsonProperty("element48")
    private String element48;
    
    /**
     *  screenField1 
	 */
    @JsonProperty("screenField1")
    private String screenField1;
    
    /**
     *  screenField2 
	 */
    @JsonProperty("screenField2")
    private String screenField2;
    
    /**
     *  screenField3 
	 */
    @JsonProperty("screenField3")
    private String screenField3;
    
    /**
     *  screenField4 
	 */
    @JsonProperty("screenField4")
    private String screenField4;
    
    /**
     *  screenField5 
	 */
    @JsonProperty("screenField5")
    private String screenField5;
    
    /**
     *  totalAmount 
	 */
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;
    
    /**
     * Element 37
     */
    @JsonProperty("element37")
    private String element37;

    /* Transient Attributes: */

    /* Constructors: */


	/**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
	public String getCustomerName() {
		return customerName;
	}

	/**
     * Sets <code>type</code>.
     * 
     * @param type The <code>type</code> to set.
     */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
     * Sets <code>type</code>.
     * 
     * @param type The <code>type</code> to set.
     */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
     * Sets <code>type</code>.
     * 
     * @param type The <code>type</code> to set.
     */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
	public BigDecimal getAmountFee() {
		return amountFee;
	}

	/**
     * Sets <code>type</code>.
     * 
     * @param type The <code>type</code> to set.
     */
	public void setAmountFee(BigDecimal amountFee) {
		this.amountFee = amountFee;
	}

	/**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
	public String getElement61() {
		return element61;
	}

	/**
     * Sets <code>type</code>.
     * 
     * @param type The <code>type</code> to set.
     */
	public void setElement61(String element61) {
		this.element61 = element61;
	}

	/**
     * Gets <code>element11</code>.
     * 
     * @return The <code>element11</code>.
     */
	public String getElement11() {
		return element11;
	}

	/**
     * Sets <code>element11</code>.
     * 
     * @param element11 The <code>element11</code> to set.
     */
	public void setElement11(String element11) {
		this.element11 = element11;
	}

	/**
     * Gets <code>element48</code>.
     * 
     * @return The <code>element48</code>.
     */
	public String getElement48() {
		return element48;
	}

	/**
     * Sets <code>element48</code>.
     * 
     * @param element48 The <code>element48</code> to set.
     */
	public void setElement48(String element48) {
		this.element48 = element48;
	}

	/**
	 * Gets <code>screenField1</code>.
	 * @return The <code>screenField1</code>.
	 */
	public String getScreenField1() {
	    return screenField1;
	}

	/**
	 * Sets <code>screenField1</code>.
	 * @param screenField1 The <code>screenField1</code> to set.
	 */
	public void setScreenField1(String screenField1) {
	    this.screenField1 = screenField1;
	}

	/**
	 * Gets <code>screenField2</code>.
	 * @return The <code>screenField2</code>.
	 */
	public String getScreenField2() {
	    return screenField2;
	}

	/**
	 * Sets <code>screenField2</code>.
	 * @param screenField2 The <code>screenField2</code> to set.
	 */
	public void setScreenField2(String screenField2) {
	    this.screenField2 = screenField2;
	}

	/**
	 * Gets <code>screenField3</code>.
	 * @return The <code>screenField3</code>.
	 */
	public String getScreenField3() {
	    return screenField3;
	}

	/**
	 * Sets <code>screenField3</code>.
	 * @param screenField3 The <code>screenField3</code> to set.
	 */
	public void setScreenField3(String screenField3) {
	    this.screenField3 = screenField3;
	}

	/**
	 * Gets <code>screenField4</code>.
	 * @return The <code>screenField4</code>.
	 */
	public String getScreenField4() {
	    return screenField4;
	}

	/**
	 * Sets <code>screenField4</code>.
	 * @param screenField4 The <code>screenField4</code> to set.
	 */
	public void setScreenField4(String screenField4) {
	    this.screenField4 = screenField4;
	}

	/**
	 * Gets <code>screenField5</code>.
	 * @return The <code>screenField5</code>.
	 */
	public String getScreenField5() {
	    return screenField5;
	}

	/**
	 * Sets <code>screenField5</code>.
	 * @param screenField5 The <code>screenField5</code> to set.
	 */
	public void setScreenField5(String screenField5) {
	    this.screenField5 = screenField5;
	}
	
	
	/**
	 * Gets <code>totalAmount</code>.
	 * @return The <code>totalAmount</code>.
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Sets <code>totalAmount</code>.
	 * @param totalAmount The <code>totalAmount</code> to set.
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * Gets <code>element37</code>.
	 * @return The <code>element37</code>.
	 */
	public String getElement37() {
	    return element37;
	}

	/**
	 * Sets <code>element37</code>.
	 * @param element37 The <code>element37</code> to set.
	 */
	public void setElement37(String element37) {
	    this.element37 = element37;
	}

	@Override
	public String toString() {
		return "inquiryEMoneyResponse [customerName=" + customerName
				+ ", customerNumber=" + customerNumber + ", amount=" + amount
				+ ", amountFee=" + amountFee + ", element61=" + element61
				+ ", element11=" + element11 + ", element48=" + element48
				+ ", element37=" + element37
				+ ", screenField1=" + screenField1 + ", screenField2="
				+ screenField2 + ", screenField3=" + screenField3
				+ ", screenField4=" + screenField4 + ", screenField5="
				+ screenField5 + ", totalAmount=" + totalAmount + "]";
	}

	

	

    

}
