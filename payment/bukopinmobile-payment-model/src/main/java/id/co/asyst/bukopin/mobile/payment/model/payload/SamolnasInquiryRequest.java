/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.payload;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 27, 2020
 * @since 1.2.Alpha1
 */
public class SamolnasInquiryRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String payCode;

    @NotBlank
    private String nik;

    @NotBlank
    private String username;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>payCode</code>.
     * 
     * @return The <code>payCode</code>.
     */
    public String getPayCode() {
	return payCode;
    }

    /**
     * Sets <code>payCode</code>.
     * 
     * @param payCode The <code>payCode</code> to set.
     */
    public void setPayCode(String payCode) {
	this.payCode = payCode;
    }

    /**
     * Gets <code>nik</code>.
     * 
     * @return The <code>nik</code>.
     */
    public String getNik() {
	return nik;
    }

    /**
     * Sets <code>nik</code>.
     * 
     * @param nik The <code>nik</code> to set.
     */
    public void setNik(String nik) {
	this.nik = nik;
    }

    /**
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
    public String getUsername() {
	return username;
    }

    /**
     * Sets <code>username</code>.
     * 
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
	this.username = username;
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
	return "SamolnasInquiryRequest [payCode=" + payCode + ", nik=" + nik + ", username=" + username + "]";
    }

}
