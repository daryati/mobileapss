/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.payload;

import java.util.List;
import java.util.Locale.Category;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.master.model.entity.Destination;
import id.co.asyst.bukopin.mobile.master.model.entity.PurchaseCategory;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 07, 2020
 * @since 1.1.Alpha1
 */
public class ExchangeRateDetailResponse {
    /* Constants: */

    /* Attributes: */
	@JsonProperty("currency_no")
    private String currencyNo;
    
	@JsonProperty("code")
    private String code;
    
	@JsonProperty("sell")
    private String sell;
    
	@JsonProperty("buy")
    private String buy;

   
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>currencyNo</code>.
     * @return The <code>currencyNo</code>.
     */
    public String getCurrencyNo() {
		return currencyNo;
	}

    /**
     * Sets <code>currencyNo</code>.
     * @param currencyNo The <code>currencyNo</code> to set.
     */
	public void setCurrencyNo(String currencyNo) {
		this.currencyNo = currencyNo;
	}

	/**
     * Gets <code>code</code>.
     * @return The <code>code</code>.
     */
	public String getCode() {
		return code;
	}

	/**
     * Sets <code>code</code>.
     * @param code The <code>code</code> to set.
     */
	public void setCode(String code) {
		this.code = code;
	}

	/**
     * Gets <code>sell</code>.
     * @return The <code>sell</code>.
     */
	public String getSell() {
		return sell;
	}

	/**
     * Sets <code>sell</code>.
     * @param sell The <code>sell</code> to set.
     */
	public void setSell(String sell) {
		this.sell = sell;
	}

	/**
     * Gets <code>buy</code>.
     * @return The <code>buy</code>.
     */
	public String getBuy() {
		return buy;
	}

	/**
     * Sets <code>buy</code>.
     * @param buy The <code>buy</code> to set.
     */
	public void setBuy(String buy) {
		this.buy = buy;
	}

	@Override
	public String toString() {
		return "ExchangeRateDetailResponse [currencyNo=" + currencyNo
				+ ", code=" + code + ", sell=" + sell + ", buy=" + buy + "]";
	}

    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
