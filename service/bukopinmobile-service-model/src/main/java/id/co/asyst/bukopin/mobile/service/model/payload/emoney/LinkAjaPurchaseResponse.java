/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.emoney;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 31, 2020
 * @since 2.0
 */
public class LinkAjaPurchaseResponse {
    /* Constants: */

    /* Attributes: */
    LinkAjaPurchaseResult respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>respayment</code>.
     * @return The <code>respayment</code>.
     */
    public LinkAjaPurchaseResult getRespayment() {
        return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * @param respayment The <code>respayment</code> to set.
     */
    public void setRespayment(LinkAjaPurchaseResult respayment) {
        this.respayment = respayment;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LinkAjaPaymentResponse [respayment=" + respayment + "]";
    }
}
