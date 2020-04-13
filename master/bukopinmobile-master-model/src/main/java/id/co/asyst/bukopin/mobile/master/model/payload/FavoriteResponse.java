/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.payload;

import java.util.List;
import java.util.Locale.Category;

import id.co.asyst.bukopin.mobile.master.model.entity.Destination;
import id.co.asyst.bukopin.mobile.master.model.entity.PurchaseCategory;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 11, 2019
 * @since 2.0
 */
public class FavoriteResponse {
    /* Constants: */

    /* Attributes: */
    private String id_category;
    
    private String category_name;
    
    private List<Destination> category;

   
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>favorite</code>.
     * @return The <code>favorite</code>.
     */
    public List<Destination> getCategory() {
        return category;
    }

    /**
     * Gets <code>id_category</code>.
     * @return The <code>id_category</code>.
     */
    public String getId_category() {
        return id_category;
    }

    /**
     * Sets <code>id_category</code>.
     * @param id_category The <code>id_category</code> to set.
     */
    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    /**
     * Gets <code>category_name</code>.
     * @return The <code>category_name</code>.
     */
    public String getCategory_name() {
        return category_name;
    }

    /**
     * Sets <code>category_name</code>.
     * @param category_name The <code>category_name</code> to set.
     */
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    /**
     * Sets <code>favorite</code>.
     * @param favorite The <code>favorite</code> to set.
     */
    public void setCategory(List<Destination> category) {
        this.category = category;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
