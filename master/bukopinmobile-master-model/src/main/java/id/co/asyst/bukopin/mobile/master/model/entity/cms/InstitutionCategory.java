/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.entity.cms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Institution Category Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Mar 27, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "INSTITUTION_CATEGORY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstitutionCategory extends IdBasedObject implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    /**
     * code
     */
    @JsonProperty("code")
    @Column(name = "code", length = 50, nullable = false)
    private String code;

    /**
     * name
     */
    @JsonProperty("name")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * description english
     */
    @JsonProperty("descriptionEnglish")
    @Column(name = "description_english", length = 100)
    private String descriptionEnglish;

    /**
     * description local
     */
    @JsonProperty("descriptionLocal")
    @Column(name = "description_local", length = 100)
    private String descriptionLocal;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * @param code
     *            The <code>code</code> to set.
     */
    public void setCode(String code) {
	this.code = code;
    }

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
     * @param name
     *            The <code>name</code> to set.
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Gets <code>descriptionEnglish</code>.
     * 
     * @return The <code>descriptionEnglish</code>.
     */
    public String getDescriptionEnglish() {
	return descriptionEnglish;
    }

    /**
     * Sets <code>descriptionEnglish</code>.
     * 
     * @param descriptionEnglish
     *            The <code>descriptionEnglish</code> to set.
     */
    public void setDescriptionEnglish(String descriptionEnglish) {
	this.descriptionEnglish = descriptionEnglish;
    }

    /**
     * Gets <code>descriptionLocal</code>.
     * 
     * @return The <code>descriptionLocal</code>.
     */
    public String getDescriptionLocal() {
	return descriptionLocal;
    }

    /**
     * Sets <code>descriptionLocal</code>.
     * 
     * @param descriptionLocal
     *            The <code>descriptionLocal</code> to set.
     */
    public void setDescriptionLocal(String descriptionLocal) {
	this.descriptionLocal = descriptionLocal;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "InstitutionCategory [code=" + code + ", name=" + name + ", descriptionEnglish=" + descriptionEnglish
		+ ", descriptionLocal=" + descriptionLocal + "]";
    }
}
