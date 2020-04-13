/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Product Entity Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends IdBasedObject implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6343891573397584501L;

    /* Constants: */

    /* Attributes: */
    /**
     * Product Name
     */
    @Column(name = "PDID")
    private int pdId;

    /**
     * Product Name
     */
    @Column(name = "PRODUCT_NAME", length = 50)
    private String productName;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>productName</code>.
     * 
     * @return The <code>productName</code>.
     */
    public String getProductName() {
	return productName;
    }

    /**
     * Sets <code>productName</code>.
     * 
     * @param productName
     *            The <code>productName</code> to set.
     */
    public void setProductName(String productName) {
	this.productName = productName;
    }

    /**
     * Gets <code>pdId</code>.
     * 
     * @return The <code>pdId</code>.
     */
    public int getPdId() {
	return pdId;
    }

    /**
     * Sets <code>pdId</code>.
     * 
     * @param pdId
     *            The <code>pdId</code> to set.
     */
    public void setPdId(int pdId) {
	this.pdId = pdId;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Product [pdId=" + pdId + ", productName=" + productName + "]";
    }
}
