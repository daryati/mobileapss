/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

/**
 * Centagate Save Question & Answer Request Model (Save PIN)
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 3, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class SaveQARequest implements Serializable {

    /**
     * Serial UID Version
     */
    private static final long serialVersionUID = -2126468573843497380L;
    
    /* Constants: */

    /* Attributes: */
    private String cenToken;
    private String data;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>cenToken</code>.
     * @return The <code>cenToken</code>.
     */
    public String getCenToken() {
        return cenToken;
    }
    /**
     * Sets <code>cenToken</code>.
     * @param cenToken The <code>cenToken</code> to set.
     */
    public void setCenToken(String cenToken) {
        this.cenToken = cenToken;
    }
    /**
     * Gets <code>data</code>.
     * @return The <code>data</code>.
     */
    public String getData() {
        return data;
    }
    /**
     * Sets <code>data</code>.
     * @param data The <code>data</code> to set.
     */
    public void setData(String data) {
        this.data = data;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SaveQAReqeust [cenToken=" + cenToken + ", data=" + data + "]";
    }
}
