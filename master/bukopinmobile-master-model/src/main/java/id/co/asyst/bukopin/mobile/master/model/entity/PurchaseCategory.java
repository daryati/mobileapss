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
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 7, 2019
 * @since 2.0
 */
@Entity
@Table(name = "CATEGORY")
public class PurchaseCategory implements Serializable{

    /* Constants: */
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /* Attributes: */
    
    @Id
    @Column(name = "ID_CATEGORY")
    private Long idCategory;
    
    @Column(name = "CATEGORY_NAME")
    private String name;
    
    @JsonIgnore
    @Column(name = "IS_ACTIVE")
    @Type(type = "yes_no")
    private Boolean isActive;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>idCategory</code>.
     * @return The <code>idCategory</code>.
     */
    public Long getIdCategory() {
        return idCategory;
    }

    /**
     * Sets <code>idCategory</code>.
     * @param idCategory The <code>idCategory</code> to set.
     */
    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
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
     * Gets <code>isActive</code>.
     * @return The <code>isActive</code>.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Sets <code>isActive</code>.
     * @param isActive The <code>isActive</code> to set.
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    
}
