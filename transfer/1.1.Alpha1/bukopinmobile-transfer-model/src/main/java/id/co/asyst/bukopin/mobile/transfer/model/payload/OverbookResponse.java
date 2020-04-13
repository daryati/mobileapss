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
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 27, 2019
 * @since 2.0
 */
public class OverbookResponse {
    /* Constants: */

    /* Attributes: */

    /* Transient Attributes: */
    private List<MyAccountResponse> myAccount;
    
    private List<ReceiverInfo> other;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>myAccount</code>.
     * @return The <code>myAccount</code>.
     */
    public List<MyAccountResponse> getMyAccount() {
        return myAccount;
    }

    /**
     * Sets <code>myAccount</code>.
     * @param myResponse The <code>myAccount</code> to set.
     */
    public void setMyAccount(List<MyAccountResponse> myResponse) {
        this.myAccount = myResponse;
    }

    /**
     * Gets <code>other</code>.
     * @return The <code>other</code>.
     */
    public List<ReceiverInfo> getOther() {
        return other;
    }

    /**
     * Sets <code>other</code>.
     * @param other The <code>other</code> to set.
     */
    public void setOther(List<ReceiverInfo> other) {
        this.other = other;
    }
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    @Override
    public String toString() {
	return "Overbook [myAccount = " + myAccount + ", other = " + other + "]";
    }
}
