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
package id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Feb 24, 2020
 * @since 2.0
 */
public class TelkomPSTNSpeedyPurchaseTibcoResponse {
    /* Constants: */

    /* Attributes: */
    private TelkomPSTNSpeedyPurchaseTibcoRespayment respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>respayment</code>.
     * @return The <code>respayment</code>.
     */
    public TelkomPSTNSpeedyPurchaseTibcoRespayment getRespayment() {
        return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * @param respayment The <code>respayment</code> to set.
     */
    public void setRespayment(TelkomPSTNSpeedyPurchaseTibcoRespayment respayment) {
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
	return "TelkomPSTNSpeedyPurchaseTIbcoResponse [respayment=" + respayment + "]";
    }
}
