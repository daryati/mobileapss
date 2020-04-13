/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Feb 14, 2020
 * @since 2.0
 */
public class TelkomPSTNSpeedyInquiryReq {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String type;
    
    @NotBlank
    @Size(message="{error.invalid.input}", min=9, max=16)
    private String custNo;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>type</code>.
     * @return The <code>type</code>.
     */
    public String getType() {
        return type;
    }
    /**
     * Sets <code>type</code>.
     * @param type The <code>type</code> to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Gets <code>custNo</code>.
     * @return The <code>custNo</code>.
     */
    public String getCustNo() {
        return custNo;
    }
    /**
     * Sets <code>custNo</code>.
     * @param custNo The <code>custNo</code> to set.
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelkomPSTNSpeedyInquiryReq [type=" + type + ", custNo=" + custNo + "]";
    }
}
