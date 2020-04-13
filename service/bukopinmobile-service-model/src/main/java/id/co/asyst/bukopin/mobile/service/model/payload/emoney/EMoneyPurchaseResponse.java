/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.emoney;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 21, 2020
 * @since 1.1.Alpha1
 */
public class EMoneyPurchaseResponse {
    /* Constants: */

    /* Attributes: */
    private EMoneyPurchaseResult respayment;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>respayment</code>.
     * 
     * @return The <code>respayment</code>.
     */
    public EMoneyPurchaseResult getRespayment() {
	return respayment;
    }

    /**
     * Sets <code>respayment</code>.
     * 
     * @param respayment
     *            The <code>respayment</code> to set.
     */
    public void setRespayment(EMoneyPurchaseResult respayment) {
	this.respayment = respayment;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    @Override
	public String toString() {
		return "EMoneyPurchaseResponse [respayment=" + respayment + "]";
	}
}
