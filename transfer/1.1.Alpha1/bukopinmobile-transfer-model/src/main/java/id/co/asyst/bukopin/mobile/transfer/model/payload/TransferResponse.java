/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.payload;

import java.util.List;

import id.co.asyst.bukopin.mobile.transfer.model.entity.ReceiverInfo;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 27, 2019
 * @since 2.0
 */
public class TransferResponse {
    /* Constants: */

    /* Attributes: */
    private List<ReceiverInfo> transfer;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>transfer</code>.
     * @return The <code>transfer</code>.
     */
    public List<ReceiverInfo> getTransfer() {
        return transfer;
    }

    /**
     * Sets <code>transfer</code>.
     * @param transfer The <code>transfer</code> to set.
     */
    public void setTransfer(List<ReceiverInfo> transfer) {
        this.transfer = transfer;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    @Override
    public String toString() {
	return "TransferResponse [transfer = " + transfer + "]";
    }
}
