/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.asyst.foundation.base.model.IdBasedObject;
import id.co.asyst.foundation.base.model.IdBasedObjectAclAware;

/**
 * List Credit Entity Model
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 6, 2020
 * @since 2.0
 */
@Entity
@Table(name = "LIST_CREDIT")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCredit extends IdBasedObjectAclAware implements Serializable{

    /* Constants: */
    
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */    
    @Column(name="CODE_CC")
    private String codeCc;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "IMAGE_LOGO")
    private String imageLogo;
    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>codeCc</code>.
     * @return The <code>codeCc</code>.
     */
    public String getCodeCc() {
        return codeCc;
    }

    /**
     * Sets <code>codeCc</code>.
     * @param codeCc The <code>codeCc</code> to set.
     */
    public void setCodeCc(String codeCc) {
        this.codeCc = codeCc;
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
     * Gets <code>imageLogo</code>.
     * @return The <code>imageLogo</code>.
     */
    public String getImageLogo() {
        return imageLogo;
    }

    /**
     * Sets <code>imageLogo</code>.
     * @param imageLogo The <code>imageLogo</code> to set.
     */
    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ListCredit [codeCc=" + codeCc + ", name=" + name + ", imageLogo=" + imageLogo + "]";
    }
}
