/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * List Insurance Entity Model
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 13, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Entity
@Table(name="LIST_INSURANCE")
public class ListInsurance extends IdBasedObject {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 9079370632245838564L;
    
    /* Constants: */

    /* Attributes: */
    /**
	 * codeIns
	 */
    @Column(name = "CODE_INS", nullable = false, length = 20)
    private String codeIns;
    
    
    /**
	 * Name
	 */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    
    
    /**
	 * image
	 */
    @Transient
    @JsonProperty("image")
    private String image;

    /* Constructors: */
    
    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>codeIns</code>.
     * @return The <code>codeIns</code>.
     */
    public String getCodeIns() {
		return codeIns;
	}

    /**
     * Sets <code>codeIns</code>.
     * @param codeIns The <code>codeIns</code> to set.
     */
	public void setCodeIns(String codeIns) {
		this.codeIns = codeIns;
	}
	
	/**
     * Gets <code>name</code>.
     * @return The <code>name</code>.
     */
	public String getName() {
		return name;
	}

	/**
     * Sets <code>name</code>.
     * @param name The <code>name</code> to set.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Gets <code>image</code>.
     * @return The <code>image</code>.
     */
	public String getImage() {
		return image;
	}

	/**
     * Sets <code>image</code>.
     * @param image The <code>image</code> to set.
     */
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ListInsurance [codeIns=" + codeIns + ", name=" + name
				+ ", image=" + image + "]";
	}

	
   
}
