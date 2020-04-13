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

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @param <T>
 * @since 1.0.Alpha1
 */
public class PrefixTelcoDetailMapper<T> {
    /* Constants: */

    /* Attributes: */
	@JsonProperty("productCode")
    private String productCode;
	
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("institutionType")
    private String institutionType;
	
	@JsonProperty("adminFee")
    private BigDecimal adminFee;
	
	@JsonProperty("selection")
    private List<T> selection;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstitutionType() {
		return institutionType;
	}

	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	public BigDecimal getAdminFee() {
		return adminFee;
	}

	public void setAdminFee(BigDecimal adminFee) {
		this.adminFee = adminFee;
	}

	public List<T> getSelection() {
		return selection;
	}

	public void setSelection(List<T> selection) {
		this.selection = selection;
	}

	@Override
	public String toString() {
		return "PrefixTelcoDetailMapper [productCode=" + productCode + ", name="
				+ name + ", institutionType=" + institutionType + ", adminFee="
				+ adminFee + ", selection=" + selection + "]";
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
