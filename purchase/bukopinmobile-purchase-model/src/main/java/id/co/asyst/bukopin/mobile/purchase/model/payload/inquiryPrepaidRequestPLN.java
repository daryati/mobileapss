/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.payload;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Dec 7, 2019
 * @since 1.0.Alpha1
 */
public class inquiryPrepaidRequestPLN {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String subscriberID;

    @NotNull
    private BigDecimal nominal;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>subscriberID</code>.
     * 
     * @return The <code>subscriberID</code>.
     */
    public String getSubscriberID() {
	return subscriberID;
    }

    /**
     * Sets <code>subscriberID</code>.
     * 
     * @param subscriberID The <code>subscriberID</code> to set.
     */
    public void setSubscriberID(String subscriberID) {
	this.subscriberID = subscriberID;
    }

    /**
     * Gets <code>nominal</code>.
     * 
     * @return The <code>nominal</code>.
     */
    public BigDecimal getNominal() {
	return nominal;
    }

    /**
     * Sets <code>nominal</code>.
     * 
     * @param nominal The <code>nominal</code> to set.
     */
    public void setNominal(BigDecimal nominal) {
	this.nominal = nominal;
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
	return "inquiryPrepaidRequestPLN [subscriberID=" + subscriberID + ", nominal=" + nominal + "]";
    }

}
