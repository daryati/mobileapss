/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.payload;

import javax.validation.constraints.NotBlank;

/**
 * Account Activation Request
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jun 17, 2020
 * @since 1.3.Alpha1-SNAPSHOT
 */
public class InquiryNotificationRequest {
    /* Constants: */

    /* Attributes: */
    /**
     * Username
     */
    private boolean saveData;
    
    /**
     * Main Account Number
     */
    @NotBlank
    private String username;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>username</code>.
     * @return The <code>username</code>.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets <code>username</code>.
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    public boolean isSaveData() {
		return saveData;
	}

	public void setSaveData(boolean saveData) {
		this.saveData = saveData;
	}

	@Override
	public String toString() {
		return "InquiryNotificationRequest [saveData=" + saveData + ", username=" + username + "]";
	}
}
