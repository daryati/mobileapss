/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.payload;

import javax.validation.constraints.NotBlank;

/**
 * Verify Phone Owner Request
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 21, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class VerifyPhoneOwnerRequest extends VerifyTokenOwnerRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String phoneIdentity;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>phoneIdentity</code>.
     * @return The <code>phoneIdentity</code>.
     */
    public String getPhoneIdentity() {
        return phoneIdentity;
    }

    /**
     * Sets <code>phoneIdentity</code>.
     * @param phoneIdentity The <code>phoneIdentity</code> to set.
     */
    public void setPhoneIdentity(String phoneIdentity) {
        this.phoneIdentity = phoneIdentity;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    
    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "VerifyPhoneOwnerRequest [phoneIdentity=" + phoneIdentity + "]";
    }
}
