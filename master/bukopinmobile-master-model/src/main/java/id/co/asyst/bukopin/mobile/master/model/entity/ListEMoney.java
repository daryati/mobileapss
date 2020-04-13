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
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.base.model.IdBasedObjectAclAware;
import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for List E-Money
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "LIST_E_MONEY")
public class ListEMoney extends IdBasedObject implements Serializable {

    /* Constants: */

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */
    /**
	 * Name
	 */
    @NotBlank
    @JsonProperty("name")
	@Column(name = "NAME", length = 100, nullable = false)
    private String name;
	
	/**
	 * upper limit
	 */
    @NotNull(message = "upper limit is Required!")
    @JsonProperty("upperLimit")
    @Column(name = "UPPER_LIMIT", nullable = false)
    private BigInteger upperLimit;
    
    /**
	 * upper limit
	 */
    @NotNull(message = "bottom limit is Required!")
    @JsonProperty("bottomLimit")
    @Column(name = "BOTTOM_LIMIT", nullable = false)
    private BigInteger bottomLimit;
    
    @Transient
    @JsonProperty("image")
    private String image;

    /**
     * Gets <code>name</code>.
     * 
     * @return The <code>name</code>.
     */
	public String getName() {
		return name;
	}

	/**
     * Sets <code>name</code>.
     * 
     * @param name The <code>name</code> to set.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Gets <code>upperLimit</code>.
     * 
     * @return The <code>upperLimit</code>.
     */
	public BigInteger getUpperLimit() {
		return upperLimit;
	}

	/**
     * Sets <code>upperLimit</code>.
     * 
     * @param upperLimit The <code>upperLimit</code> to set.
     */
	public void setUpperLimit(BigInteger upperLimit) {
		this.upperLimit = upperLimit;
	}

	/**
     * Gets <code>bottomLimit</code>.
     * 
     * @return The <code>bottomLimit</code>.
     */
	public BigInteger getBottomLimit() {
		return bottomLimit;
	}

	/**
     * Sets <code>bottomLimit</code>.
     * 
     * @param bottomLimit The <code>bottomLimit</code> to set.
     */
	public void setBottomLimit(BigInteger bottomLimit) {
		this.bottomLimit = bottomLimit;
	}

	/**
     * Gets <code>image</code>.
     * 
     * @return The <code>image</code>.
     */
	public String getImage() {
		return image;
	}

	/**
     * Sets <code>image</code>.
     * 
     * @param image The <code>image</code> to set.
     */
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ListEMoney [name=" + name + ", upperLimit=" + upperLimit
				+ ", bottomLimit=" + bottomLimit + "]";
	}
    
    }
