/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.payload;

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
	@JsonProperty("idPrefix")
    private Long idPrefix;
	
	@JsonProperty("institutionType")
    private String institutionType;

	public Long getIdPrefix() {
		return idPrefix;
	}

	public void setIdPrefix(Long idPrefix) {
		this.idPrefix = idPrefix;
	}

	public String getInstitutionType() {
		return institutionType;
	}

	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	

	
	
    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>currencyNo</code>.
     * @return The <code>currencyNo</code>.
     */
    

    /**
     * Sets <code>currencyNo</code>.
     * @param currencyNo The <code>currencyNo</code> to set.
     */
	




    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
