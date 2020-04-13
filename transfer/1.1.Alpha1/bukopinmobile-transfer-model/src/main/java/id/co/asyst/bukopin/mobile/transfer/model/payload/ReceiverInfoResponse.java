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

import id.co.asyst.bukopin.mobile.transfer.model.entity.ReceiverInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 27, 2019
 * @since 2.0
 */
public class ReceiverInfoResponse {
    /* Constants: */

    /* Attributes: */
    
    private OverbookResponse overbook;
    
    private TransferResponse transfer;
    

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>overbook</code>.
     * @return The <code>overbook</code>.
     */
    public OverbookResponse getOverbook() {
        return overbook;
    }

    /**
     * Sets <code>overbook</code>
     * @param overbook The <code>overbook</code> to set.
     */
    public void setOverbook(OverbookResponse overbook) {
        this.overbook = overbook;
    }

    /**
     * Gets <code>transfer</code>.
     * @return The <code>transfer</code>.
     */
    public TransferResponse getTransfer() {
        return transfer;
    }

    /**
     * Sets <code>transfer</code>.
     * @param transfer The <code>transfer</code> to set.
     */
    public void setTransfer(TransferResponse transfer) {
        this.transfer = transfer;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    @Override
    public String toString() {
	return "ReceiverInfoResponse [overbook = " + overbook + ", transfer = " + transfer + "]";
    }
}
