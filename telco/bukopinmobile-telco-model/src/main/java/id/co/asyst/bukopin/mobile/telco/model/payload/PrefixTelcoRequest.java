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

import java.util.List;
//import org.json.JSONArray;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
public class PrefixTelcoRequest {
    /* Constants: */

    /* Attributes: */
	
	/**
	 * phoneNumber
	 */
	@NotBlank(message = "Phone Number is required.")
	@JsonProperty("phoneNumber")
    private String phoneNumber;
	
	/**
	 * type
	 */
	@JsonProperty("type")
    private String type;

	/* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>phoneNumber</code>.
     * @return The <code>phoneNumber</code>.
     */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
     * Sets <code>phoneNumber</code>.
     * @param phoneNumber The <code>phoneNumber</code> to set.
     */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
     * Gets <code>type</code>.
     * @return The <code>type</code>.
     */
	public String getType() {
		return type;
	}

	/**
     * Sets <code>type</code>.
     * @param type The <code>type</code> to set.
     */
	public void setType(String type) {
		this.type = type;
	}

	
	}
