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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
public class InstitutionRequest {
    /* Constants: */

    /* Attributes: */
	
	/**
     * idPrefix
     */
	@JsonProperty("idPrefix")
    private Long idPrefix;
	
	/**
     * institutionType
     */
	@JsonProperty("institutionType")
    private String institutionType;

	
	/* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>idPrefix</code>.
     * @return The <code>idPrefix</code>.
     */
	public Long getIdPrefix() {
		return idPrefix;
	}

	/**
     * Sets <code>idPrefix</code>.
     * @param idPrefix The <code>idPrefix</code> to set.
     */
	public void setIdPrefix(Long idPrefix) {
		this.idPrefix = idPrefix;
	}

	/**
     * Gets <code>institutionType</code>.
     * @return The <code>institutionType</code>.
     */
	public String getInstitutionType() {
		return institutionType;
	}

	/**
     * Sets <code>institutionType</code>.
     * @param institutionType The <code>institutionType</code> to set.
     */
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	@Override
	public String toString() {
		return "InstitutionRequest [idPrefix=" + idPrefix + ", institutionType="
				+ institutionType + "]";
	}

	

	
	}
