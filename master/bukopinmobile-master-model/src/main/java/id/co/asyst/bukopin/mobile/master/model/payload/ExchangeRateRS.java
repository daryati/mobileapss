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
public class ExchangeRateRS {
    /* Constants: */

    /* Attributes: */
	@JsonProperty("reqTime")
	private String reqTime;
	@JsonProperty("tt")
    private List<ExchangeRateDetailResponse> TT;
    
	@JsonProperty("bankNote")
    private List<ExchangeRateDetailResponse> bankNote;
   
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>currencyNo</code>.
     * @return The <code>currencyNo</code>.
     */
    public List<ExchangeRateDetailResponse> getTT() {
		return TT;
	}

    /**
     * Sets <code>currencyNo</code>.
     * @param currencyNo The <code>currencyNo</code> to set.
     */
	public void setTT(List<ExchangeRateDetailResponse> tT) {
		TT = tT;
	}

	/**
     * Gets <code>currencyNo</code>.
     * @return The <code>currencyNo</code>.
     */
	public List<ExchangeRateDetailResponse> getBankNote() {
		return bankNote;
	}

	/**
     * Sets <code>currencyNo</code>.
     * @param currencyNo The <code>currencyNo</code> to set.
     */
	public void setBankNote(List<ExchangeRateDetailResponse> bankNote) {
		this.bankNote = bankNote;
	}

	@Override
	public String toString() {
		return "ExchangeRateResponse [TT=" + TT + ", bankNote=" + bankNote
				+ "]";
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
    
	
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
}
