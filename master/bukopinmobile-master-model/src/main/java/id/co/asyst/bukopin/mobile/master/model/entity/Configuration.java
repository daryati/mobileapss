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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.base.model.IdBasedObjectAclAware;
import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for Configuration
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 27, 2019
 * @since 2.0
 */
@Entity
@Table(name = "CONFIGURATION")
public class Configuration extends IdBasedObject implements Serializable {

    /* Constants: */

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */
    /**
	 * Name
	 */
    @JsonProperty("name")
	@Column(name = "NAME", length = 100, nullable = false)
    private String name;
	
	/**
	 * Value
	 */
    @JsonProperty("value")
    @Column(name = "VALUE", length = 100, nullable = false)
    private String value;
    
    /**
	 * Description
	 */
    @JsonProperty("description")
    @Column(name = "DESCRIPTION", length = 100, nullable = true)
    private String description;
    
    /**
	 * Create By
	 */
    @JsonProperty("createBy")
    @Column(name = "CREATE_BY", length = 100, nullable = true)
    private String createBy;
    
    /**
	 * Create Date
	 */
    @JsonProperty("createDate")
    @Column(name = "CREATE_DATE")
    private Date createDate;
    
    /**
	 * Update By
	 */
    @JsonProperty("updateBy")
    @Column(name = "UPDATE_BY", length = 100, nullable = true)
    private String updateBy;
    
    /**
	 * Update Date
	 */
    @JsonProperty("updateDate")
    @Column(name = "UPDATE_DATE")    
    private Date updateDate;

    /* Getters & setters for attributes: */
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
     * Gets <code>value</code>.
     * 
     * @return The <code>value</code>.
     */
	public String getValue() {
		return value;
	}

	/**
     * Sets <code>value</code>.
     * 
     * @param value The <code>value</code> to set.
     */
	public void setValue(String value) {
		this.value = value;
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

	/**
     * Gets <code>createBy</code>.
     * 
     * @return The <code>createBy</code>.
     */
	public String getCreateBy() {
		return createBy;
	}

	/**
     * Sets <code>createBy</code>.
     * 
     * @param createBy The <code>createBy</code> to set.
     */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
     * Gets <code>createDate</code>.
     * 
     * @return The <code>createDate</code>.
     */
	public Date getCreateDate() {
		return createDate;
	}

	/**
     * Sets <code>createDate</code>.
     * 
     * @param createDate The <code>createDate</code> to set.
     */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
     * Gets <code>updateBy</code>.
     * 
     * @return The <code>updateBy</code>.
     */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
     * Sets <code>updateBy</code>.
     * 
     * @param updateBy The <code>updateBy</code> to set.
     */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
     * Gets <code>updateDate</code>.
     * 
     * @return The <code>updateDate</code>.
     */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
     * Sets <code>updateDate</code>.
     * 
     * @param updateDate The <code>updateDate</code> to set.
     */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Configuration [name=" + name + ", value=" + value
				+ ", description=" + description + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + "]";
	}
    
    
	
    
    
    
    }
