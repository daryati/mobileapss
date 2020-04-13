/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for Currency
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 06, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "CURRENCY")
public class Currency extends IdBasedObject implements Serializable {

    /* Constants: */

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */
    /**
	 * currency no
	 */
    @NotNull(message = "currency no is required!")
    @JsonProperty("currencyNo")
	@Column(name = "CURRENCY_NO", nullable = false)
    private Integer currencyNo;
	
	/**
	 * code
	 */
    @NotBlank
    @JsonProperty("code")
    @Column(name = "CODE", length = 100, nullable = false)
    private String code;
    
    /**
	 * Description
	 */
    @NotBlank
    @JsonProperty("description")
    @Column(name = "DESCRIPTION", length = 100, nullable = true)
    private String description;

    
    /* Getters & setters for attributes: */
    /**
     * Gets <code>currencyNo</code>.
     * 
     * @return The <code>currencyNo</code>.
     */
	public Integer getCurrencyNo() {
		return currencyNo;
	}

	/**
     * Sets <code>currencyNo</code>.
     * 
     * @param currencyNo The <code>currencyNo</code> to set.
     */
	public void setCurrencyNo(Integer currencyNo) {
		this.currencyNo = currencyNo;
	}

	/**
     * Gets <code>code</code>.
     * 
     * @return The <code>code</code>.
     */
	public String getCode() {
		return code;
	}

	/**
     * Sets <code>code</code>.
     * 
     * @param code The <code>code</code> to set.
     */
	public void setCode(String code) {
		this.code = code;
	}

	/**
     * Gets <code>description</code>.
     * 
     * @return The <code>description</code>.
     */
	public String getDescription() {
		return description;
	}

	/**
     * Sets <code>description</code>.
     * 
     * @param description The <code>description</code> to set.
     */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Currency [currencyNo=" + currencyNo + ", code=" + code
				+ ", description=" + description + "]";
	}
    
    
    
    
    
    }
