/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model.payload;

import java.math.BigDecimal;
import java.util.List;
import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14, 2020
 * @since 1.0.Alpha1
 */
public class InquiryMobileDataPrepaidTelcoResponse extends InquiryMobileDataPrepaidTelcoRequest {
    /* Constants: */

    /* Attributes: */
	
	/**
     * totalAmount
     */
	@JsonProperty("totalAmount")
    private BigDecimal totalAmount;
	
	@JsonProperty("element11")
    private String element11;
	
	@JsonProperty("element37")
    private String element37;
	
	@JsonProperty("element61")
    private String element61;

	/* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
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
	 * @return the element11
	 */
	public String getElement11() {
		return element11;
	}

	/**
	 * @param element11 the element11 to set
	 */
	public void setElement11(String element11) {
		this.element11 = element11;
	}

	/**
	 * @return the element37
	 */
	public String getElement37() {
		return element37;
	}

	/**
	 * @param element37 the element37 to set
	 */
	public void setElement37(String element37) {
		this.element37 = element37;
	}

	/**
	 * @return the element61
	 */
	public String getElement61() {
		return element61;
	}

	/**
	 * @param element61 the element61 to set
	 */
	public void setElement61(String element61) {
		this.element61 = element61;
	}

	@Override
	public String toString() {
		return "InquiryMobileDataPrepaidTelcoResponse [totalAmount="
				+ totalAmount + "]";
	}

	

	
	}
