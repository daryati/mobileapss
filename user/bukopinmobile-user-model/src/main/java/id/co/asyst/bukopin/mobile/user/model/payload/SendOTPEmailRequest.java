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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Nov 14, 2019
 * @since 2.0
 */
public class SendOTPEmailRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    @Email(message = "Email not valid")
    private String email;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */


    /**
     * Gets <code>email</code>.
     * @return The <code>email</code>.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets <code>email</code>.
     * @param email The <code>email</code> to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SendOTPEmailRequest [email=" + email + "]";
    }

    
}
